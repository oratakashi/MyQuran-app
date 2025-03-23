package com.oratakashi.myquran.data.web

import com.oratakashi.myquran.data.model.BaseResponse
import com.oratakashi.myquran.data.model.ayat.AyatItem
import com.oratakashi.myquran.data.model.surah.SurahItem
import io.reactivex.rxjava3.core.Single

class QuranApi(
    private val api: QuranApiClient
) : QuranApiClient {
    override fun getSurah(): Single<BaseResponse<List<SurahItem>>> {
        return api.getSurah()
    }

    override fun getAyat(nomor: Int): Single<BaseResponse<List<AyatItem>>> {
        return api.getAyat(nomor)
    }
}