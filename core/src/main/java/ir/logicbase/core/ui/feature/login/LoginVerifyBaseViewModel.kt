package ir.logicbase.core.ui.feature.login

import androidx.lifecycle.LiveData
import io.reactivex.disposables.CompositeDisposable
import ir.logicbase.livex.OneShotLiveEvent
import ir.logicbase.core.ui.base.BaseViewModel

abstract class LoginVerifyBaseViewModel(
    compositeDisposable: CompositeDisposable
) : BaseViewModel(compositeDisposable) {
    protected val _showTryLaterError = OneShotLiveEvent<Unit>()
    val showTryLaterError: LiveData<Unit> = _showTryLaterError

    protected val _showWrongCode = OneShotLiveEvent<Unit>()
    val showWrongCode: LiveData<Unit> = _showWrongCode
}