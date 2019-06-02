package ir.logicfan.core.data.reactive

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