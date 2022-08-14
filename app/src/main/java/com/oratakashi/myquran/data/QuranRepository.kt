package com.oratakashi.myquran.data

import com.oratakashi.myquran.data.model.ayat.AyatItem
import com.oratakashi.myquran.data.model.surah.SurahEntity
import com.oratakashi.myquran.data.model.surah.SurahItem
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

interface QuranRepository {
    fun getSurah(): Observable<List<SurahEntity>>
    fun getAyat(nomor: Int): Single<List<AyatItem>>
}