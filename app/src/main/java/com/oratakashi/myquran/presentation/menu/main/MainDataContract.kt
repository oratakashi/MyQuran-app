package com.oratakashi.myquran.presentation.menu.main

import com.oratakashi.myquran.domain.model.surah.Surah

interface MainDataContract {
    fun onLoadingSurah()
    fun onSuccessSurah(data: List<Surah>)
    fun onFailSurah(error: Throwable?)
}