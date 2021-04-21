package ir.logicbase.logikan.data.reactive.disposable

import ir.logicbase.logikan.data.base.DataOutcome
import ir.logicbase.logikan.data.reactive.TerminalStateObserver

/**
 * Define a disposable observer with DataOutcome object
 *
 * @param T data type of your stream
 * @property onNextSingleDataFunc single data callback
 * @property onNextListDataFunc list data callback
 * @property onNextPagedListDataFunc paged list data callback
 * @property onNextImperativeState imperative response callback
 */
class DisposableDataOutcomeObserver<T>(
    private val terminalStateObserver: TerminalStateObserver,
    private val onNextSingleDataFunc: (DataOutcome.SingleDataState<T>) -> Unit = {},
    private val onNextListDataFunc: (DataOutcome.ListDataState<T>) -> Unit = {},
    private val onNextPagedListDataFunc: (DataOutcome.PagedListDataState<T>) -> Unit = {},
    private val onNextImperativeState: (DataOutcome.ImperativeState) -> Unit = {},
    onNextFunc: (DataOutcome<T>) -> Unit = {},
    onCompleteFunc: () -> Unit = {},
    onErrorFunc: (Throwable) -> Unit = {}
) : DisposableDelegateErrorObserver<DataOutcome<T>>(terminalStateObserver, onNextFunc, onCompleteFunc, onErrorFunc) {

    override fun onNext(outcome: DataOutcome<T>) {
        if (isDisposed) {
            return
        }
        when (outcome) {
            is DataOutcome.SingleDataState -> onNextSingleDataFunc(outcome)
            is DataOutcome.ListDataState -> onNextListDataFunc(outcome)
            is DataOutcome.PagedListDataState -> onNextPagedListDataFunc(outcome)
            is DataOutcome.ImperativeState -> onNextImperativeState(outcome)
        }
        outcome.update?.let { terminalStateObserver.onUpdateState(it) }
        onNextFunc(outcome)
    }
}