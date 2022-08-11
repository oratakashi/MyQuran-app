package com.oratakashi.myquran.data.web

import com.oratakashi.myquran.data.model.surah.SurahItem
import io.reactivex.Single
import retrofit2.http.GET

interface QuranApiClient {
    @GET("data")
    fun getSurah(): Single<List<SurahItem>>
}