package com.oratakashi.myquran.presentation.menu.main

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.oratakashi.myquran.domain.model.surah.Surah
import com.oratakashi.viewbinding.core.tools.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainObserver(
    private val viewModel: MainViewModel,
    private val view: MainDataContract
) : DefaultLifecycleObserver {
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        viewModel.surah.observe(owner) {
            when (it) {
                is State.Loading -> view.onLoadingSurah()
                is State.Default -> Unit
                is State.Empty   -> Unit
                is State.Success -> {
                    val state: MutableStateFlow<List<Surah>> = MutableStateFlow(emptyList())
                    owner.lifecycleScope.launch(Dispatchers.IO) {
                        it.data.chunked(10).forEach { part ->
                            state.emit(part)
                            delay(500)
                        }
                    }
                    view.onSuccessSurah(state)
                }
                is State.Failure -> view.onFailSurah(it.throwable)
            }
        }
    }
}