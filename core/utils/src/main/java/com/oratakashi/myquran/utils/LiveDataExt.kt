package com.oratakashi.myquran.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.immutable() = this as LiveData<T>
