package com.oratakashi.myquran

import android.app.Application
import com.oratakashi.myquran.di.databaseModule
import com.oratakashi.myquran.di.navigationModule
import com.oratakashi.myquran.di.networkModule
import com.oratakashi.myquran.di.quranModule
import com.oratakashi.myquran.utils.AppConfig
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        AppConfig.apply {
            appId = BuildConfig.APPLICATION_ID
            isDebug = BuildConfig.DEBUG
            versionName = BuildConfig.VERSION_NAME
            buildType = BuildConfig.VERSION_NAME
        }

        startKoin {
            androidContext(this@App)
            modules(
                networkModule,
                navigationModule,
                databaseModule,
                quranModule
            )
        }
    }
}