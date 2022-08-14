package com.oratakashi.myquran.domain

import com.oratakashi.myquran.data.QuranRepository
import com.oratakashi.myquran.domain.model.ayat.Ayat
import com.oratakashi.myquran.domain.model.surah.Surah
import io.reactivex.Observable
import io.reactivex.Single

class QuranInteractor(
    private val repo: QuranRepository
): QuranUseCase {
    override fun getSurah(): Observable<List<Surah>> {
        return repo.getSurah().map { it.map { data -> data.toSurah() } }
    }

    override fun getAyat(nomor: Int): Single<List<Ayat>> {
        return repo.getAyat(nomor).map { it.map { data -> data.toAyat() } }
    }
}