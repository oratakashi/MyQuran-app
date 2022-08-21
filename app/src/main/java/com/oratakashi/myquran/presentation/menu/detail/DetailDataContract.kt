package com.oratakashi.myquran.presentation.menu.detail

import com.oratakashi.myquran.domain.model.ayat.Ayat
import kotlinx.coroutines.flow.StateFlow

interface DetailDataContract {
    fun onLoadingAyat()
    fun onSuccessAyat(data: StateFlow<List<Ayat>>)
    fun onFailAyat(error: Throwable?)
}