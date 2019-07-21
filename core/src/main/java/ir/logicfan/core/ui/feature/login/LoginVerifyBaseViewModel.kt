package ir.logicfan.core.ui.feature.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import ir.logicfan.core.ui.base.BaseViewModel
import ir.logicfan.core.ui.reactive.OneShotEvent
import javax.inject.Inject

open class LoginVerifyBaseViewModel @Inject
constructor(
    compositeDisposable: CompositeDisposable
) : BaseViewModel(compositeDisposable) {

    protected val _showTryLaterError = MutableLiveData<OneShotEvent<Unit>>()
    val showTryLaterError: LiveData<OneShotEvent<Unit>> = _showTryLaterError

    protected val _showWrongCode = MutableLiveData<OneShotEvent<Unit>>()
    val showWrongCode: LiveData<OneShotEvent<Unit>> = _showWrongCode

    protected val _showLoginSuccessful = MutableLiveData<OneShotEvent<Unit>>()
    val showLoginSuccessful: LiveData<OneShotEvent<Unit>> = _showLoginSuccessful

    protected val _navigateOutOfLogin = MutableLiveData<OneShotEvent<Unit>>()
    val navigateOutOfLogin: LiveData<OneShotEvent<Unit>> = _navigateOutOfLogin
}