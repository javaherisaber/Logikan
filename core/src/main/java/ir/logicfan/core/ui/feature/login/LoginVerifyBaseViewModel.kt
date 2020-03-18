/*
 * Copyright (C) Logicfan, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential
 * Written by Mahdi Javaheri Saber <m.javaherisaber@gmail.com>, Mohammad Hosein Abedini <mohammadhoseinabedini@gmail.com>  2020.
 */

package ir.logicfan.core.ui.feature.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import ir.logicfan.core.ui.base.BaseViewModel
import ir.logicfan.core.ui.reactive.OneShotEvent

abstract class LoginVerifyBaseViewModel(
    compositeDisposable: CompositeDisposable
) : BaseViewModel(compositeDisposable) {
    protected val _showTryLaterError = MutableLiveData<OneShotEvent<Unit>>()
    val showTryLaterError: LiveData<OneShotEvent<Unit>> = _showTryLaterError

    protected val _showWrongCode = MutableLiveData<OneShotEvent<Unit>>()
    val showWrongCode: LiveData<OneShotEvent<Unit>> = _showWrongCode
}