package ir.logicfan.core.ui.base

import android.util.Log
import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ir.logicfan.core.data.reactive.ErrorStateObserver
import ir.logicfan.core.ui.reactive.OneShotEvent
import ir.logicfan.core.ui.reactive.SingleLiveEvent

abstract class BaseViewModel(val compositeDisposable: CompositeDisposable) : ViewModel(), ErrorStateObserver {

    private val _errorState: SingleLiveEvent<Throwable> =
        SingleLiveEvent()
    val errorState: LiveData<Throwable>
        get() = _errorState

    fun disposableContext(operation: () -> Disposable) = compositeDisposable.add(operation())

    @CallSuper
    override fun onCleared() {
        compositeDisposable.clear()
        Log.d(TAG, "ViewModel recycled in : ${this.javaClass.simpleName}")
        super.onCleared()
    }

    override fun onErrorState(throwable: Throwable) {
        this._errorState.value = throwable
        Log.e("error message1", throwable.message, throwable)
    }

    companion object {
        private var TAG = BaseViewModel::class.java.simpleName
        @Deprecated(
            "There is a more readable extension function for this purpose " +
                    "in LiveDataExtensions with name postOneShotUnit()",
            ReplaceWith(
                "oneShotObject.postOneShotUnit()",
                "ir.logicfan.core.ui.util.extension.postOneShotUnit"
            )
        )
        fun emitUnitEvent(oneShotObject: MutableLiveData<OneShotEvent<Unit>>) {
            oneShotObject.value = OneShotEvent(Unit)
        }
    }
}