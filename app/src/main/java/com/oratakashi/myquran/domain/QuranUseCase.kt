package com.oratakashi.myquran.domain

import androidx.paging.PagingData
import com.oratakashi.myquran.domain.model.ayat.Ayat
import com.oratakashi.myquran.domain.model.surah.Surah
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable

interface QuranUseCase {
    fun getSurah(): Observable<List<Surah>>
    fun getAyat(nomor: Int): Flowable<List<Ayat>>
    fun getAyatPaging(nomor: Int): Flowable<PagingData<Ayat>>
}