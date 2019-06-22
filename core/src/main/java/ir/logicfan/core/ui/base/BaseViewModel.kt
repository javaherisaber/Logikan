package ir.logicfan.core.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ir.logicfan.core.data.reactive.ErrorStateObserver
import ir.logicfan.core.ui.reactive.Event
import ir.logicfan.core.ui.reactive.SingleLiveEvent

abstract class BaseViewModel(val compositeDisposable: CompositeDisposable) : ViewModel(), ErrorStateObserver {

    private val _errorState: SingleLiveEvent<Throwable> =
        SingleLiveEvent()
    val errorState: LiveData<Throwable>
        get() = _errorState

    fun disposableContext(operation: () -> Disposable) = compositeDisposable.add(operation())

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    override fun onErrorState(throwable: Throwable) {
        this._errorState.value = throwable
    }

    companion object {
        fun emitUnitEvent(liveObject: MutableLiveData<Event<Unit>>) {
            liveObject.value = Event(Unit)
        }
    }
}