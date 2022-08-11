package com.oratakashi.myquran.data

import com.oratakashi.myquran.data.model.surah.SurahItem
import io.reactivex.Single

interface QuranRepository {
    fun getSurah(): Single<List<SurahItem>>
}