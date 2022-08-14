package com.oratakashi.myquran.data.web

import com.oratakashi.myquran.data.model.ayat.AyatItem
import com.oratakashi.myquran.data.model.surah.SurahItem
import io.reactivex.Single

class QuranApi(
    private val api: QuranApiClient
) : QuranApiClient {
    override fun getSurah(): Single<List<SurahItem>> {
        return api.getSurah()
    }

    override fun getAyat(nomor: Int): Single<List<AyatItem>> {
        return api.getAyat(nomor)
    }
}