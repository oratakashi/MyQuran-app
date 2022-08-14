package com.oratakashi.myquran.presentation.menu.detail

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.oratakashi.viewbinding.core.tools.State

class DetailObserver(
    private val viewModel: DetailViewModel,
    private val view: DetailDataContract
): DefaultLifecycleObserver {
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        viewModel.ayat.observe(owner) {
            when(it) {
                is State.Loading -> view.onLoadingAyat()
                is State.Default -> Unit
                is State.Empty   -> Unit
                is State.Success -> view.onSuccessAyat(it.data)
                is State.Failure -> view.onFailAyat(it.throwable)
            }
        }
    }
}