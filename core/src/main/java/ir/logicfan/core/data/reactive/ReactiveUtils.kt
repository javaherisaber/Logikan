package ir.logicfan.core.data.reactive

import io.reactivex.Observable

object ReactiveUtils {

    @JvmStatic
    fun <T> composeWith(transformer: Transformer<T>, receiver: () -> Observable<T>) =
        receiver().compose(transformer)!!
}