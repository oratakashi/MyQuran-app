package com.oratakashi.myquran.presentation.menu.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oratakashi.myquran.domain.QuranUseCase
import com.oratakashi.myquran.domain.model.surah.Surah
import com.oratakashi.myquran.utility.immutable
import com.oratakashi.viewbinding.core.binding.livedata.liveData
import com.oratakashi.viewbinding.core.tools.State
import com.oratakashi.viewbinding.core.tools.retrofit.transformer.composeObservable
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(
    private val useCase: QuranUseCase
) : ViewModel() {
    private val disposable: CompositeDisposable by lazy { CompositeDisposable() }

    private val _surah: MutableLiveData<State<List<Surah>>> by liveData()
    val surah: LiveData<State<List<Surah>>> by lazy { _surah.immutable() }

    fun getSurah() {
        _surah.postValue(State.loading())
        useCase.getSurah()
            .compose(composeObservable())
            .subscribe({
                _surah.postValue(State.success(it))
            }, {
                _surah.postValue(State.fail(it, it.message))
            })
            .let { return@let disposable::add }
    }

}