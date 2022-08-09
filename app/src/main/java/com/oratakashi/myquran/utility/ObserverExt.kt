package com.oratakashi.myquran.utility

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver

fun Lifecycle.addObservers(vararg lifecycleObserver: LifecycleObserver) {
    lifecycleObserver.forEach {
        addObserver(it)
    }
}

fun Fragment.addObservers(vararg lifecycleObserver: LifecycleObserver) {
    viewLifecycleOwner.lifecycle.addObservers(*lifecycleObserver)
}

fun AppCompatActivity.addObservers(vararg lifecycleObserver: LifecycleObserver) {
    lifecycle.addObservers(*lifecycleObserver)
}