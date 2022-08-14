package com.oratakashi.myquran.presentation.menu.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oratakashi.myquran.domain.QuranUseCase
import com.oratakashi.myquran.domain.model.ayat.Ayat
import com.oratakashi.myquran.utility.immutable
import com.oratakashi.viewbinding.core.binding.livedata.liveData
import com.oratakashi.viewbinding.core.tools.State
import com.oratakashi.viewbinding.core.tools.retrofit.transformer.composeSingle
import io.reactivex.disposables.CompositeDisposable

class DetailViewModel(
    private val quranUsecase: QuranUseCase
) : ViewModel() {
    private val disposable: CompositeDisposable by lazy { CompositeDisposable() }

    private val _ayat: MutableLiveData<State<List<Ayat>>> by liveData()
    val ayat: LiveData<State<List<Ayat>>> by lazy { _ayat.immutable() }

    fun getAyat(nomor: Int) {
        quranUsecase.getAyat(nomor)
            .compose(composeSingle())
            .subscribe({
                _ayat.postValue(State.success(it.apply {
                    if(nomor != 1 && this.first().nomor == "1") {
                        val replaceFirst = this.first().arabic.replace("بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ", "")
                        this.first().arabic = replaceFirst
                    }
                }))
            },{
                _ayat.postValue(State.fail(it, it.message))
            }).let { return@let disposable::add }
    }
}