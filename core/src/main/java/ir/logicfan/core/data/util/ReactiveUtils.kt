package ir.logicfan.core.data.util

import io.reactivex.Observable
import ir.logicfan.core.data.reactive.Transformer

object ReactiveUtils {

    @JvmStatic
    fun <T> composeWith(transformer: Transformer<T>, receiver: () -> Observable<T>) =
        receiver().compose(transformer)!!
}