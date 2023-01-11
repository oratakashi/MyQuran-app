package com.oratakashi.myquran.di

//val networkModule = module {
//    single {
//        ChuckerCollector(
//            context = androidContext(),
//            showNotification = BuildConfig.DEBUG,
//            retentionPeriod = RetentionManager.Period.ONE_DAY
//        )
//    }
//
//    single {
//        ChuckerInterceptor.Builder(androidContext())
//            .apply {
//                collector(get())
//                maxContentLength(250_000L)
//                alwaysReadResponseBody(false)
//            }
//            .build()
//    }
//
//    single {
//        createOkHttpClient(
//            arrayOf(
//                get<ChuckerInterceptor>()
//            ),
//            null,
//            null,
//            BuildConfig.DEBUG
//        )
//    }
//
//    single {
//        createReactiveService(
//            QuranApiClient::class.java,
//            get(),
//            Credentials.getBaseUrl()
//        )
//    }
//}