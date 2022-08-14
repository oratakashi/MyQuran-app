package com.oratakashi.myquran.presentation.menu.detail

import com.oratakashi.myquran.domain.model.ayat.Ayat

interface DetailDataContract {
    fun onLoadingAyat()
    fun onSuccessAyat(data: List<Ayat>)
    fun onFailAyat(error: Throwable?)
}