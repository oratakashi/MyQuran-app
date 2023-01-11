package com.oratakashi.myquran.utils.di

import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.oratakashi.myquran.utils.AppConfig
import com.oratakashi.viewbinding.core.tools.retrofit.createOkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

interface NetworkModules {
    fun provideNetworkModules(): Array<Module> {
        return arrayOf(
            provideChuckerCollector(),
            provideChuckerInterceptor(),
            provideOkHttp()
        )
    }

    fun provideChuckerCollector(): Module {
        return module {
            single {
                ChuckerCollector(
                    context = androidContext(),
                    showNotification = AppConfig.isDebug,
                    retentionPeriod = RetentionManager.Period.ONE_DAY
                )
            }
        }
    }

    fun provideChuckerInterceptor(): Module {
        return module {
            single {
                ChuckerInterceptor.Builder(androidContext())
                    .apply {
                        collector(get())
                        maxContentLength(250_000L)
                        alwaysReadResponseBody(false)
                    }
                    .build()
            }
        }
    }

    fun provideOkHttp(): Module {
        return module {
            single {
                createOkHttpClient(
                    arrayOf(
                        get<ChuckerInterceptor>()
                    ),
                    null,
                    null,
                    AppConfig.isDebug
                )
            }
        }
    }
}