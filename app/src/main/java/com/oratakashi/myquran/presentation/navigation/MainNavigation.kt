package com.oratakashi.myquran.presentation.navigation

import com.oratakashi.myquran.domain.model.surah.Surah

interface MainNavigation {
    fun toMain()
    fun toDetail(surah: Surah)
}