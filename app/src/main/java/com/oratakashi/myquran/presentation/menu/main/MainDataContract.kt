package com.oratakashi.myquran.presentation.menu.main

import com.oratakashi.myquran.domain.model.surah.Surah
import kotlinx.coroutines.flow.StateFlow

interface MainDataContract {
    fun onLoadingSurah()
    fun onSuccessSurah(data: StateFlow<List<Surah>>)
    fun onFailSurah(error: Throwable?)
}