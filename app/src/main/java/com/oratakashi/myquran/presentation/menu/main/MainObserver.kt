package com.oratakashi.myquran.presentation.menu.main

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.oratakashi.viewbinding.core.tools.State

class MainObserver(
    private val viewModel: MainViewModel,
    private val view: MainDataContract
): DefaultLifecycleObserver {
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        viewModel.surah.observe(owner) {
            when(it) {
                is State.Loading -> view.onLoadingSurah()
                is State.Default -> Unit
                is State.Empty   -> Unit
                is State.Success -> view.onSuccessSurah(it.data)
                is State.Failure -> view.onFailSurah(it.throwable)
            }
        }
    }
}