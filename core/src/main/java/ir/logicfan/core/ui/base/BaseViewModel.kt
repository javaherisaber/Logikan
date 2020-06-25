package ir.logicfan.core.ui.base

import android.util.Log
import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ir.logicfan.core.data.entity.UpdateData
import ir.logicfan.core.data.reactive.TerminalStateObserver
import ir.logicfan.core.ui.reactive.SingleLiveEvent

abstract class BaseViewModel(val compositeDisposable: CompositeDisposable) : ViewModel(), TerminalStateObserver {

    private val _errorState: SingleLiveEvent<Throwable> = SingleLiveEvent()
    val errorState: LiveData<Throwable> = _errorState

    private val _updateState: SingleLiveEvent<UpdateData> = SingleLiveEvent()
    val updateState: LiveData<UpdateData> = _updateState

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
        _errorState.value = throwable
    }

    override fun onUpdateState(update: UpdateData) {
        _updateState.value = update
    }

    companion object {
        private var TAG = BaseViewModel::class.java.simpleName
    }
}