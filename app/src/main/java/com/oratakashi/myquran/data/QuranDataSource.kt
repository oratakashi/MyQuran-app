package com.oratakashi.myquran.data

import com.oratakashi.myquran.data.db.SurahDao
import com.oratakashi.myquran.data.model.surah.SurahEntity
import com.oratakashi.myquran.data.model.surah.SurahItem
import com.oratakashi.myquran.data.web.QuranApi
import com.oratakashi.viewbinding.core.network.networkSync
import io.reactivex.Observable
import io.reactivex.Single

class QuranDataSource(
    private val webService: QuranApi,
    private val dbSurah: SurahDao
): QuranRepository {
    override fun getSurah(): Observable<List<SurahEntity>> {
        return networkSync(
            saveToDb = { dbSurah.insertAll(it) },
            fetchDb = { dbSurah.getAll() },
            fetchApi = { webService.getSurah() },
            onConflict = { api, _ -> api.map { it.toSurahEntity() } },
            alwaysUpToDate = { it.isEmpty() }
        )
    }

}