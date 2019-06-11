package ir.logicfan.core.data.reactive.disposable

import ir.logicfan.core.data.base.DataOutcome
import ir.logicfan.core.data.error.DataException
import ir.logicfan.core.data.error.DataNonTerminalErrorType
import ir.logicfan.core.data.error.DataTerminalErrorType
import ir.logicfan.core.data.reactive.ErrorStateObserver

/**
 * Define a disposable observer with DataOutcome object
 *
 * @param T data type of your stream
 * @property onNextSingleDataFunc emit single data
 * @property onNextListDataFunc emit list data
 * @property onNextImperativeState emit imperative response
 * @property onNonTerminalErrorFunc emit non terminal error
 */
class DisposableDataOutcomeObserver<T>(
    errorStateObserver: ErrorStateObserver? = null,
    private val onNextSingleDataFunc: (DataOutcome.SingleDataState<T>) -> Unit = {},
    private val onNextListDataFunc: (DataOutcome.ListDataState<T>) -> Unit = {},
    private val onNextPagedListDataFunc: (DataOutcome.PagedListDataState<T>) -> Unit = {},
    private val onNextImperativeState: (DataOutcome.ImperativeState) -> Unit = {},
    private val onNonTerminalErrorFunc: (List<DataException.NonTerminal>) -> Unit = {},
    onNextFunc: (DataOutcome<T>) -> Unit = {},
    onCompleteFunc: () -> Unit = {},
    onErrorFunc: (Throwable) -> Unit = {}
) : DisposableDelegateErrorObserver<DataOutcome<T>>(onNextFunc, errorStateObserver, onCompleteFunc, onErrorFunc) {

    override fun onNext(t: DataOutcome<T>) {
        if (!isDisposed) {
            when (t) {
                is DataOutcome.ErrorState -> {
                    DataNonTerminalErrorType.findNonTerminalExceptionOrNull(t.errorList)?.let { onNonTerminalErrorFunc(it) }
                    DataTerminalErrorType.findTerminalExceptionOrNull(t.errorList)?.let { onError(it) }
                }
                is DataOutcome.SingleDataState -> onNextSingleDataFunc(t)
                is DataOutcome.ListDataState -> onNextListDataFunc(t)
                is DataOutcome.PagedListDataState -> onNextPagedListDataFunc(t)
                is DataOutcome.ImperativeState -> onNextImperativeState
            }
            onNextFunc(t)
        }
    }
}