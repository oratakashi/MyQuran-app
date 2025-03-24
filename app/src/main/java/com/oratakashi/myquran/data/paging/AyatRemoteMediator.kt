package com.oratakashi.myquran.data.paging

import android.provider.MediaStore.Audio.Media
import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.paging.rxjava3.RxRemoteMediator
import com.oratakashi.myquran.data.db.QuranDatabase
import com.oratakashi.myquran.data.model.ayat.AyatEntity
import com.oratakashi.myquran.data.model.ayat.AyatRemoteKeyEntity
import com.oratakashi.myquran.data.web.QuranApi
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.InvalidObjectException

@OptIn(ExperimentalPagingApi::class)
class AyatRemoteMediator(
    private val api: QuranApi,
    private val db: QuranDatabase,
    private val idSurah: Int
) : RxRemoteMediator<Int, AyatEntity>() {

    private val TAG = "AyatRemoteMediator"

    override fun loadSingle(
        loadType: LoadType,
        state: PagingState<Int, AyatEntity>
    ): Single<MediatorResult> {
        return Single.fromCallable {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    Log.d(TAG, "LoadType.REFRESH triggered")
                    1
                }
                LoadType.PREPEND -> {
                    // PREPEND = muat halaman SEBELUM halaman saat ini
                    Log.d(TAG, "LoadType.PREPEND triggered")
                    val firstItem = state.firstItemOrNull()
                    if (firstItem == null) {
                        Log.d(TAG, "PREPEND: No first item found")
                        return@fromCallable -1
                    }

                    val remoteKey = db.runInTransaction<AyatRemoteKeyEntity?> {
                        db.ayatRemoteKey().getRemoteKeyByAyatId(firstItem.id)
                    }

                    val prevPage = remoteKey?.prevKey
                    Log.d(TAG, "PREPEND: First item ID: ${firstItem.id}, prevPage: $prevPage")

                    prevPage ?: -1
                }
                LoadType.APPEND -> {
                    // APPEND = muat halaman SETELAH halaman saat ini
                    Log.d(TAG, "LoadType.APPEND triggered")
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        Log.d(TAG, "APPEND: No last item found")
                        return@fromCallable 1
                    }

                    val remoteKey = db.runInTransaction<AyatRemoteKeyEntity?> {
                        db.ayatRemoteKey().getRemoteKeyByAyatId(lastItem.id)
                    }

                    val nextPage = remoteKey?.nextKey
                    Log.d(TAG, "APPEND: Last item ID: ${lastItem.id}, nextPage: $nextPage")

                    nextPage ?: -1
                }
            }

            Log.d(TAG, "Calculated page to load: $page")
            page
        }
            .subscribeOn(Schedulers.io())
            .flatMap { page ->
                if (page == -1) {
                    Single.just<MediatorResult>(MediatorResult.Success(endOfPaginationReached = true))
                } else {
                    api.getAyat(idSurah, page)
                        .map { response ->
                            val ayatEntities = response.data?.map { it.toAyatEntity() } ?: emptyList()
//                            val endOfPaginationReached = response.meta?.currentPage == response.meta?.totalPage
                            Pair(ayatEntities, response.meta)
                        }
                        .flatMap { data ->
//                            db.ayat().insert(data.first).map { data }
                            val endOfPaginationReached = data.second?.currentPage == data.second?.totalPage
                            db.runInTransaction {
//                                if (loadType == LoadType.REFRESH) {
//                                    // Bersihkan data jika refresh
//                                    db.ayatRemoteKey().clearRemoteKeys()
//                                    db.ayat().clearAll()
//                                }

                                // Simpan remote keys untuk setiap ayat
                                val prevKey = if (page == 1) null else page - 1
                                val nextKey = if (endOfPaginationReached) null else page + 1
                                val ayatIds = data.first.map { it.id }

                                val remoteKeys = ayatIds.map {
                                    AyatRemoteKeyEntity(id = it, currentPage = page, prevKey = prevKey, nextKey = nextKey)
                                }
                                db.ayatRemoteKey().insertAll(remoteKeys)

                                // Insert ayat ke database
                                db.ayat().insert(data.first)
                            }

                            Single.just(Pair(data.first, endOfPaginationReached))
                        }
                        .map { (_, endOfPaginationReached) ->
                            MediatorResult.Success(endOfPaginationReached)
                        }
                }
            }
            .onErrorReturn { MediatorResult.Error(it) }
    }
}