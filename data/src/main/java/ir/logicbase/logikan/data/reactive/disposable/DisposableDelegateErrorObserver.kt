package ir.logicbase.logikan.data.reactive.disposable

import io.reactivex.observers.DisposableObserver
import ir.logicbase.logikan.data.error.DataException
import ir.logicbase.logikan.data.reactive.TerminalStateObserver

/**
 * Define a disposable observer with an extra ErrorStateObserver to let you delegate error to suitable handler
 *
 * @param T data type of your stream
 * @property terminalStateObserver emit error for the callback receiver
 * @property onNextFunc emit next data
 * @property onCompleteFunc declare completion of stream
 * @property onErrorFunc emit error for the lambda
 */
open class DisposableDelegateErrorObserver<T>(
    private val terminalStateObserver: TerminalStateObserver,
    val onNextFunc: (T) -> Unit,
    val onCompleteFunc: () -> Unit = {},
    val onErrorFunc: (Throwable) -> Unit = {}
) : DisposableObserver<T>() {

    override fun onComplete() {
        if (!isDisposed) {
            onCompleteFunc()
        }
    }

    override fun onNext(outcome: T) {
        if (!isDisposed) {
            onNextFunc(outcome)
        }
    }

    override fun onError(error: Throwable) {
        if (!isDisposed) {
            val resultant = DataException.findExceptionOrNull(error) ?: error
            terminalStateObserver.onErrorState(resultant)
            onErrorFunc(resultant)
        }
    }
}