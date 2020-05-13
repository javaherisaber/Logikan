package ir.logicfan.core.ui.feature.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import ir.logicfan.core.ui.base.BaseViewModel
import ir.logicfan.core.ui.reactive.OneShotEvent
import ir.logicfan.core.ui.util.extension.postOneShotUnit
import ir.logicfan.core.util.extension.isValidIranMobile

abstract class LoginRequestBaseViewModel(
    compositeDisposable: CompositeDisposable
) : BaseViewModel(compositeDisposable) {

    private val _showWrongMobileError = MutableLiveData<OneShotEvent<Unit>>()
    val showWrongMobileError: LiveData<OneShotEvent<Unit>> = _showWrongMobileError

    protected val _showTryLaterError = MutableLiveData<OneShotEvent<Unit>>()
    val showTryLaterError: LiveData<OneShotEvent<Unit>> = _showTryLaterError

    private val _navigateToVerify = MutableLiveData<OneShotEvent<String>>()
    val navigateToVerify: LiveData<OneShotEvent<String>> = _navigateToVerify

    val inputMobile = MutableLiveData<String>() // two way data binding

    protected fun doIfInputMobileIsValid(operation: (String) -> Unit) {
        inputMobile.value?.let {
            when {
                it.isEmpty() -> _showWrongMobileError.postOneShotUnit()
                it.isValidIranMobile() -> operation(it)
                else -> _showWrongMobileError.postOneShotUnit()
            }
        } ?: _showWrongMobileError.postOneShotUnit()
    }

    protected fun emitNavigateToVerify(mobile: String) {
        _navigateToVerify.value = OneShotEvent(mobile)
    }
}