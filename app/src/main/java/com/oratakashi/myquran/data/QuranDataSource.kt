package com.oratakashi.myquran.data

import android.annotation.SuppressLint
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.oratakashi.myquran.data.db.QuranDatabase
import com.oratakashi.myquran.data.db.dao.AyatDao
import com.oratakashi.myquran.data.db.dao.SurahDao
import com.oratakashi.myquran.data.model.ayat.AyatEntity
import com.oratakashi.myquran.data.model.surah.SurahEntity
import com.oratakashi.myquran.data.paging.AyatRemoteMediator
import com.oratakashi.myquran.data.web.QuranApi
import com.oratakashi.viewbinding.core.network.rxjava3.observable.networkSync
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class QuranDataSource(
    private val webService: QuranApi,
    private val dbSurah: SurahDao,
    private val dbAyat: AyatDao,
    private val db: QuranDatabase
) : QuranRepository {
    override fun getSurah(): Observable<List<SurahEntity>> {
        return networkSync(
            saveToDb = { dbSurah.insertAll(it) },
            fetchDb = { dbSurah.getAll() },
            fetchApi = { webService.getSurah().map { it.data ?: emptyList() } },
            onConflict = { api, _ -> api.map { it.toSurahEntity() } },
            alwaysUpToDate = { it.isEmpty() }
        )
    }

    @SuppressLint("CheckResult")
    override fun getAyat(nomor: Int): Flowable<List<AyatEntity>> {
        return Flowable.create({ emitter ->
            dbAyat.getAll(nomor)
                .subscribe({
                    emitter.onNext(it)
                    if (it.isEmpty()) {
                        webService.getAyat(nomor)
                            .map { result -> result.data ?: emptyList() }
                            .map { result -> result.map { data -> data.toAyatEntity() } }
                            .toFlowable()
                            .takeWhile { result -> result.isNotEmpty() }
                            .flatMap { result -> Flowable.fromIterable(result) }
                            .concatMap { result ->
                                dbAyat.insert(result).toFlowable()
                                    .subscribeOn(Schedulers.io())
                            }
                            .debounce(50, TimeUnit.MILLISECONDS)
                            .flatMap { dbAyat.getAll(nomor).toFlowable() }
                            .doOnComplete { emitter.onComplete() }
                            .subscribe({ result ->
                                emitter.onNext(result)
                            },{ error ->
                                emitter.onError(error)
                            })
                    } else {
                        emitter.onComplete()
                    }
                }, {
                    emitter.onError(it)
                    emitter.onComplete()
                })
        }, BackpressureStrategy.BUFFER)
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getAyatPaging(idSurah: Int): Flowable<PagingData<AyatEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = true,
                initialLoadSize = 10
            ),
            remoteMediator = AyatRemoteMediator(
                api = webService,
                db = db,
                idSurah = idSurah
            ),
            pagingSourceFactory = { dbAyat.getPagedData(idSurah) }
        ).flowable
    }
}