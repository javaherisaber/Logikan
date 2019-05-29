package ir.logicfan.core.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ir.logicfan.core.data.reactive.ObservableEmitState
import ir.logicfan.core.data.reactive.SingleLiveEvent
import javax.inject.Inject

abstract class BaseViewModel : ViewModel(), ObservableEmitState {

    @Inject
    lateinit var compositeDisposable: CompositeDisposable

    private val _errorState: SingleLiveEvent<Throwable?> = SingleLiveEvent()
    val errorState: LiveData<Throwable?>
        get() = _errorState

    protected fun disposableContext(operation: () -> Disposable) = compositeDisposable.add(operation())

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    override fun onErrorState(throwable: Throwable?) {
        this._errorState.value = throwable
    }

    override fun onSuccessState() {
        this._errorState.value = null
    }

    override fun onNextState() {
        this._errorState.value = null
    }
}