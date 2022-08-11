package com.oratakashi.myquran.domain

import com.oratakashi.myquran.data.QuranRepository
import com.oratakashi.myquran.domain.model.surah.Surah
import io.reactivex.Single

class QuranInteractor(
    private val repo: QuranRepository
): QuranUseCase {
    override fun getSurah(): Single<List<Surah>> {
        return repo.getSurah().map { it.map { data -> data.toSurah() } }
    }
}