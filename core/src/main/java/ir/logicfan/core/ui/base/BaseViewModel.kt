package ir.logicfan.core.ui.base

import android.util.Log
import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ir.logicfan.core.data.reactive.ErrorStateObserver
import ir.logicfan.core.ui.reactive.SingleLiveEvent

abstract class BaseViewModel(val compositeDisposable: CompositeDisposable) : ViewModel(), ErrorStateObserver {

    private val _errorState: SingleLiveEvent<Throwable> = SingleLiveEvent()
    val errorState: LiveData<Throwable> = _errorState

    fun disposableContext(operation: () -> Disposable) = compositeDisposable.add(operation())

    open fun onNetworkBecomesAvailable() {
        // override this method if you have network first time loading in your view
    }

    @CallSuper
    override fun onCleared() {
        compositeDisposable.clear()
        Log.d(TAG, "ViewModel recycled in : ${this.javaClass.simpleName}")
        super.onCleared()
    }

    override fun onErrorState(throwable: Throwable) {
        this._errorState.value = throwable
    }

    companion object {
        private var TAG = BaseViewModel::class.java.simpleName
    }
}