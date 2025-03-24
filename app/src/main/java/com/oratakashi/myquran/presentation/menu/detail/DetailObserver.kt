package com.oratakashi.myquran.presentation.menu.detail

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.oratakashi.myquran.domain.model.ayat.Ayat
import com.oratakashi.viewbinding.core.tools.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DetailObserver(
    private val viewModel: DetailViewModel,
    private val view: DetailDataContract
) : DefaultLifecycleObserver {
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        viewModel.ayat.observe(owner) {
            when (it) {
                is State.Loading -> view.onLoadingAyat()
                is State.Default -> Unit
                is State.Empty   -> Unit
                is State.Success -> {
//                    val state: MutableStateFlow<List<Ayat>> = MutableStateFlow(emptyList())
//                    owner.lifecycleScope.launch(Dispatchers.IO) {
//                        it.data.chunked(10).forEach { part ->
//                            state.emit(part)
//                            delay(500)
//                        }
//                    }
//                    view.onSuccessAyat(state)
                    view.onSuccessAyat(it.data)
                }
                is State.Failure -> view.onFailAyat(it.throwable)
            }
        }
    }
}