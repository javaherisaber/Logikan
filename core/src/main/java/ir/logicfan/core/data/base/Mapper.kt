/*
 * Copyright (C) Logicfan, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential
 * Written by Mahdi Javaheri Saber <m.javaherisaber@gmail.com>, Mohammad Hosein Abedini <mohammadhoseinabedini@gmail.com>  2020.
 */

package ir.logicfan.core.data.base

import io.reactivex.Observable

abstract class Mapper<in T, R> {
    abstract fun mapFrom(from: T): R

    fun observable(from: T): Observable<R> = Observable.fromCallable {
        mapFrom(from)
    }

    fun observable(from: List<T>): Observable<List<R>> = Observable.fromCallable {
        from.map { mapFrom(it) }
    }
}