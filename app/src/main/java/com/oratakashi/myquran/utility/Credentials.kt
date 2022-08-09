package com.oratakashi.myquran.utility

object Credentials {
    init {
        System.loadLibrary("myquran")
    }

    external fun getBaseUrl(): String
}