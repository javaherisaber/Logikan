package ir.logicfan.core.ui.error

import io.reactivex.observers.DisposableObserver
import ir.logicfan.core.data.error.DataException

/**
 * Handle all data layer related error's in one place
 *
 * @param T data type of your rx call
 * @property resolution strategy to resolve the error
 * @property onNextFunc act on behalf of rx function
 * @property onCompleteFunc act on behalf of rx function
 * @property onErrorFunc act on behalf of rx function
 */
class DisposableDataResolvedObserver<T> (
    private val resolution: DataResolution,
    val onNextFunc: (T) -> Unit,
    val onCompleteFunc: () -> Unit = {},
    val onErrorFunc: (Throwable?) -> Unit = {}
) : DisposableObserver<T>() {

    override fun onComplete() {
        onCompleteFunc()
    }

    override fun onNext(t: T) {
        if (!isDisposed) {
            onNextFunc(t)
        }
    }

    override fun onError(e: Throwable) {
        when(e) {
            is DataException -> resolution.onResolutionStart(e)
            else -> onErrorFunc(e) // this will never happen, we put it here only to satisfy when else predicate
        }
    }
}