package ir.logicbase.core.data.reactive

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