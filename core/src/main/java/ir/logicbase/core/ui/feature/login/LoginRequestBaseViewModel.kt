package ir.logicbase.core.ui.feature.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import ir.logicbase.livex.OneShotLiveEvent
import ir.logicbase.livex.call
import ir.logicbase.core.ui.base.BaseViewModel
import ir.logicbase.core.util.extension.isValidIranMobile

abstract class LoginRequestBaseViewModel(
    compositeDisposable: CompositeDisposable
) : BaseViewModel(compositeDisposable) {

    private val _showWrongMobileError = OneShotLiveEvent<Unit>()
    val showWrongMobileError: LiveData<Unit> = _showWrongMobileError

    protected val _showTryLaterError = OneShotLiveEvent<Unit>()
    val showTryLaterError: LiveData<Unit> = _showTryLaterError

    private val _navigateToVerify = OneShotLiveEvent<String>()
    val navigateToVerify: LiveData<String> = _navigateToVerify

    val inputMobile = MutableLiveData<String>() // two way data binding

    protected fun doIfInputMobileIsValid(operation: (String) -> Unit) {
        inputMobile.value?.let {
            when {
                it.isEmpty() -> _showWrongMobileError.call()
                it.isValidIranMobile() -> operation(it)
                else -> _showWrongMobileError.call()
            }
        } ?: _showWrongMobileError.call()
    }

    protected fun emitNavigateToVerify(mobile: String) {
        _navigateToVerify.value = mobile
    }
}