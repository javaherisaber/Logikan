package ir.logicfan.core.data.reactive

import io.reactivex.observers.DisposableObserver

/**
 * Give you an interface to delegate error of an Observable for Observable handler
 *
 * @param T data type of your rx call
 * @property ObservableEmitState notify state of rx callbacks
 * @property onNextFunc act on behalf of rx function
 * @property onCompleteFunc act on behalf of rx function
 * @property onErrorFunc act on behalf of rx function
 */
class DisposableDefaultStateDelegate<T>(
    private val observableEmitState: ObservableEmitState?,
    val onNextFunc: (T) -> Unit,
    val onCompleteFunc: () -> Unit = {},
    val onErrorFunc: (Throwable?) -> Unit = {}
) : DisposableObserver<T>() {

    override fun onComplete() {
        if (!isDisposed) {
            observableEmitState?.onSuccessState()
            onCompleteFunc()
        }
    }

    override fun onNext(t: T) {
        if (!isDisposed) {
            observableEmitState?.onNextState()
            onNextFunc(t)
        }
    }

    override fun onError(e: Throwable) {
        if (!isDisposed) {
            observableEmitState?.onErrorState(e)
            onErrorFunc(e)
        }
    }
}