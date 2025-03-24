package com.oratakashi.myquran.presentation.menu.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.oratakashi.myquran.domain.QuranUseCase
import com.oratakashi.myquran.domain.model.ayat.Ayat
import com.oratakashi.myquran.utils.immutable
import com.oratakashi.viewbinding.core.binding.livedata.liveData
import com.oratakashi.viewbinding.core.tools.State
import com.oratakashi.viewbinding.core.tools.retrofit.rxjava3.transformer.composeFlowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subscribers.DisposableSubscriber

class DetailViewModel(
    private val quranUsecase: QuranUseCase
) : ViewModel() {
    private val disposable: CompositeDisposable by lazy { CompositeDisposable() }

    private val _ayat: MutableLiveData<State<PagingData<Ayat>>> by liveData()
    val ayat: LiveData<State<PagingData<Ayat>>> by lazy { _ayat.immutable() }

    fun getAyat(nomor: Int) {
//        _ayat.postValue(State.loading())
//        quranUsecase.getAyat(nomor)
//            .compose(composeFlowable())
//            .subscribe({
//                _ayat.postValue(State.success(it))
//            },{
//                _ayat.postValue(State.fail(it, it.message))
//            }).let { return@let disposable::add }
    }

    fun getAyatPaging(nomor: Int) {
        quranUsecase.getAyatPaging(nomor)
            .compose(composeFlowable())
            .subscribeWith(object : DisposableSubscriber<PagingData<Ayat>>() {
                override fun onStart() {
                    super.onStart()
                    _ayat.postValue(State.loading())
                }
                override fun onNext(t: PagingData<Ayat>) {
                    _ayat.postValue(State.success(t))
                }

                override fun onError(t: Throwable) {
                    _ayat.postValue(State.fail(t, t.message))
                }

                override fun onComplete() {
                    _ayat.postValue(State.default())
                }
            })
            .let { return@let disposable::add }
    }
}