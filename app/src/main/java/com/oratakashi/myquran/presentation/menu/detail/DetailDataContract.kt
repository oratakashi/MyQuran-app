package com.oratakashi.myquran.presentation.menu.detail

import androidx.paging.PagingData
import com.oratakashi.myquran.domain.model.ayat.Ayat
import kotlinx.coroutines.flow.StateFlow

interface DetailDataContract {
    fun onLoadingAyat()
    fun onSuccessAyat(data: PagingData<Ayat>)
    fun onFailAyat(error: Throwable?)
}