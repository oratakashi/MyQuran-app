package com.oratakashi.myquran.di

import com.oratakashi.myquran.data.QuranDataSource
import com.oratakashi.myquran.data.QuranRepository
import com.oratakashi.myquran.data.web.QuranApi
import com.oratakashi.myquran.domain.QuranInteractor
import com.oratakashi.myquran.domain.QuranUseCase
import com.oratakashi.myquran.presentation.menu.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val quranModule = module {
    single { QuranApi(get()) }
    single<QuranRepository> { QuranDataSource(
        get(),
        get(),
    ) }
    single<QuranUseCase> { QuranInteractor(get()) }

    viewModel { MainViewModel(get()) }
}