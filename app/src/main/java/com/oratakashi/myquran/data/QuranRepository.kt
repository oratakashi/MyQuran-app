package com.oratakashi.myquran.data

import com.oratakashi.myquran.data.model.ayat.AyatEntity
import com.oratakashi.myquran.data.model.ayat.AyatItem
import com.oratakashi.myquran.data.model.surah.SurahEntity
import com.oratakashi.myquran.data.model.surah.SurahItem
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable

interface QuranRepository {
    fun getSurah(): Observable<List<SurahEntity>>
    fun getAyat(nomor: Int): Flowable<List<AyatEntity>>
}