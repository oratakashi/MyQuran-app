package com.oratakashi.myquran.data.web

import com.oratakashi.myquran.data.model.ayat.AyatItem
import com.oratakashi.myquran.data.model.surah.SurahItem
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface QuranApiClient {
    @GET("data")
    fun getSurah(): Single<List<SurahItem>>

    @GET("surat/{nomor}")
    fun getAyat(
        @Path("nomor") nomor: Int
    ): Single<List<AyatItem>>

}