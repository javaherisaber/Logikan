package ir.logicfan.core.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ir.logicfan.core.data.reactive.ErrorStateObserver
import ir.logicfan.core.data.reactive.SingleLiveEvent

abstract class BaseViewModel(val compositeDisposable: CompositeDisposable) : ViewModel(), ErrorStateObserver {

    private val _errorState: SingleLiveEvent<Throwable> = SingleLiveEvent()
    val errorState: LiveData<Throwable>
        get() = _errorState

    protected fun disposableContext(operation: () -> Disposable) = compositeDisposable.add(operation())

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    override fun onErrorState(throwable: Throwable) {
        this._errorState.value = throwable
    }
}