package com.oratakashi.myquran.di

import com.oratakashi.myquran.data.db.QuranDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        QuranDatabase.getAppDatabase(androidContext())
    }

    single { get<QuranDatabase>().surah() }
}