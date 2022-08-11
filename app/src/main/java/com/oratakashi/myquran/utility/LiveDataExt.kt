package com.oratakashi.myquran.utility

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.immutable() = this as LiveData<T>
