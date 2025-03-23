package com.oratakashi.myquran.data.web

import com.oratakashi.myquran.data.model.BaseResponse
import com.oratakashi.myquran.data.model.ayat.AyatItem
import com.oratakashi.myquran.data.model.surah.SurahItem
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface QuranApiClient {
    @GET("surah")
    fun getSurah(): Single<BaseResponse<List<SurahItem>>>

    @GET("surah/{nomor}")
    fun getAyat(
        @Path("nomor") nomor: Int
    ): Single<BaseResponse<List<AyatItem>>>

}