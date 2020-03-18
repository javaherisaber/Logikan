/*
 * Copyright (C) Logicfan, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential
 * Written by Mahdi Javaheri Saber <m.javaherisaber@gmail.com>, Mohammad Hosein Abedini <mohammadhoseinabedini@gmail.com>  2020.
 */

package ir.logicfan.core.data.reactive.disposable

import io.reactivex.observers.DisposableObserver
import ir.logicfan.core.data.reactive.ErrorStateObserver

/**
 * Define a disposable observer with an extra ErrorStateObserver to let you delegate error to suitable handler
 *
 * @param T data type of your stream
 * @property errorStateObserver emit error for the callback receiver
 * @property onNextFunc emit next data
 * @property onCompleteFunc declare completion of stream
 * @property onErrorFunc emit error for the lambda
 */
open class DisposableDelegateErrorObserver<T>(
    private val errorStateObserver: ErrorStateObserver,
    val onNextFunc: (T) -> Unit,
    val onCompleteFunc: () -> Unit = {},
    val onErrorFunc: (Throwable) -> Unit = {}
) : DisposableObserver<T>() {

    override fun onComplete() {
        if (!isDisposed) {
            onCompleteFunc()
        }
    }

    override fun onNext(t: T) {
        if (!isDisposed) {
            onNextFunc(t)
        }
    }

    override fun onError(e: Throwable) {
        if (!isDisposed) {
            errorStateObserver.onErrorState(e)
            onErrorFunc(e)
        }
    }
}