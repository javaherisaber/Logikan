/*
 * Copyright (C) Logicfan, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential
 * Written by Mahdi Javaheri Saber <m.javaherisaber@gmail.com>, Mohammad Hosein Abedini <mohammadhoseinabedini@gmail.com>  2020.
 */

package ir.logicfan.core.data.util

import io.reactivex.Observable
import ir.logicfan.core.data.reactive.Transformer

object ReactiveUtils {

    /**
     * Define a function to delegate schedulers to transformer
     * this is a higher order function, which means you can call it like a lambda
     * @param T type of data being emitted
     * @param transformer transforms your observable with suitable schedulers
     * @param receiver observable being consumed at call site
     */
    @JvmStatic
    fun <T> composeWith(transformer: Transformer<T>, receiver: () -> Observable<T>) =
        receiver().compose(transformer)!!
}