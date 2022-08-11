package com.oratakashi.myquran.data

import com.oratakashi.myquran.data.model.surah.SurahItem
import com.oratakashi.myquran.data.web.QuranApi
import io.reactivex.Single

class QuranDataSource(
    private val webService: QuranApi
): QuranRepository {
    override fun getSurah(): Single<List<SurahItem>> {
        return webService.getSurah()
    }

}