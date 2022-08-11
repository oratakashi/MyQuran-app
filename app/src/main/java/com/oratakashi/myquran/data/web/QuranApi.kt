package com.oratakashi.myquran.data.web

import com.oratakashi.myquran.data.model.surah.SurahItem
import io.reactivex.Single

class QuranApi(
    private val api: QuranApiClient
) : QuranApiClient {
    override fun getSurah(): Single<List<SurahItem>> {
        return api.getSurah()
    }
}