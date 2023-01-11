package com.oratakashi.myquran.utils

object Credentials {
    init {
        System.loadLibrary("myquran")
    }

    external fun getBaseUrl(): String
}