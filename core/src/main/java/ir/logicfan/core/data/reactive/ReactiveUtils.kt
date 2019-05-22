package ir.logicfan.core.data.reactive

import io.reactivex.Observable

object ReactiveUtils {

    @JvmStatic
    fun <T> composeWith(aSyncTransformer: ASyncTransformer<T>, receiver: () -> Observable<T>) =
        receiver().compose(aSyncTransformer)!!
}