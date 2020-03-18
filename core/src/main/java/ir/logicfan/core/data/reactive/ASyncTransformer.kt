/*
 * Copyright (C) Logicfan, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential
 * Written by Mahdi Javaheri Saber <m.javaherisaber@gmail.com>, Mohammad Hosein Abedini <mohammadhoseinabedini@gmail.com>  2020.
 */

package ir.logicfan.core.data.reactive

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Compose a reactive stream with Async schedulers, suitable for real implementation
 * for test purposes you're gonna need sync schedulers
 */
class ASyncTransformer<T> : Transformer<T>() {
    override fun apply(upstream: Observable<T>): ObservableSource<T> =
        upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}