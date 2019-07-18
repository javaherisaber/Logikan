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
    val showTryLaterError: LiveData<OneShotEvent<Unit>>
        get() = _showTryLaterError

    protected val _showWrongCode = MutableLiveData<OneShotEvent<Unit>>()
    val showWrongCode: LiveData<OneShotEvent<Unit>>
        get() = _showWrongCode

    protected val _showTimerExpired = MutableLiveData<OneShotEvent<Unit>>()
    val showTimerExpired: LiveData<OneShotEvent<Unit>>
        get() = _showTimerExpired

    protected val _showLoginSuccessful = MutableLiveData<OneShotEvent<Unit>>()
    val showLoginSuccessful: LiveData<OneShotEvent<Unit>>
        get() = _showLoginSuccessful

    protected val _navigateUp = MutableLiveData<OneShotEvent<Unit>>()
    val navigateUp: LiveData<OneShotEvent<Unit>>
        get() = _navigateUp

    protected val _navigateOutOfLogin = MutableLiveData<OneShotEvent<Unit>>()
    val navigateOutOfLogin: LiveData<OneShotEvent<Unit>>
        get() = _navigateOutOfLogin
}