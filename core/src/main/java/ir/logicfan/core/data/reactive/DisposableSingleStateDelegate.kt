package ir.logicfan.core.data.reactive

import io.reactivex.observers.DisposableObserver

/**
 * Give you an interface to delegate error of an Observable for Single handler
 *
 * @param T data type of your rx call
 * @property singleEmitState notify state of rx callbacks
 * @property onNextFunc act on behalf of rx function
 * @property onCompleteFunc act on behalf of rx function
 * @property onErrorFunc act on behalf of rx function
 */
class DisposableSingleStateDelegate<T> (
    private val singleEmitState: SingleEmitState?,
    val onNextFunc: (T) -> Unit,
    val onCompleteFunc: () -> Unit = {},
    val onErrorFunc: (Throwable?) -> Unit = {}
) : DisposableObserver<T>() {

    override fun onComplete() {
        if (!isDisposed) {
            singleEmitState?.onSuccessState()
            onCompleteFunc()
        }
    }

    override fun onNext(t: T) {
        if (!isDisposed) {
            singleEmitState?.onSuccessState()
            onNextFunc(t)
        }
    }

    override fun onError(e: Throwable) {
        if (!isDisposed) {
            singleEmitState?.onErrorState(e)
            onErrorFunc(e)
        }
    }
}