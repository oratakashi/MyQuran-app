package com.oratakashi.myquran.presentation.navigation

import com.oratakashi.myquran.domain.model.surah.Surah

interface MainNavigation {
    fun toPrevious()
    fun toMain()
    fun toDetail(surah: Surah)
}