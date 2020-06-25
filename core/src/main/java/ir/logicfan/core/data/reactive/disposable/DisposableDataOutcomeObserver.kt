package ir.logicfan.core.data.reactive.disposable

import ir.logicfan.core.data.base.DataOutcome
import ir.logicfan.core.data.entity.UpdateData
import ir.logicfan.core.data.error.DataException
import ir.logicfan.core.data.error.DataNonTerminalErrorType
import ir.logicfan.core.data.error.DataTerminalErrorType
import ir.logicfan.core.data.reactive.TerminalStateObserver

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
    private val terminalStateObserver: TerminalStateObserver,
    private val onNextSingleDataFunc: (DataOutcome.SingleDataState<T>) -> Unit = {},
    private val onNextListDataFunc: (DataOutcome.ListDataState<T>) -> Unit = {},
    private val onNextPagedListDataFunc: (DataOutcome.PagedListDataState<T>) -> Unit = {},
    private val onNextImperativeState: (DataOutcome.ImperativeState) -> Unit = {},
    private val onNonTerminalErrorFunc: (List<DataException.NonTerminal>) -> Unit = {},
    onNextFunc: (DataOutcome<T>) -> Unit = {},
    onCompleteFunc: () -> Unit = {},
    onErrorFunc: (Throwable) -> Unit = {}
) : DisposableDelegateErrorObserver<DataOutcome<T>>(terminalStateObserver, onNextFunc, onCompleteFunc, onErrorFunc) {

    override fun onNext(outcome: DataOutcome<T>) {
        if (!isDisposed) {
            when (outcome) {
                is DataOutcome.ErrorState -> {
                    handleUpdateState(outcome.update)
                    DataNonTerminalErrorType.findNonTerminalExceptionOrNull(outcome.errorList)?.let { onNonTerminalErrorFunc(it) }
                    DataTerminalErrorType.findTerminalExceptionOrNull(outcome.errorList)?.let { onError(it) }
                }
                is DataOutcome.SingleDataState -> {
                    handleUpdateState(outcome.update)
                    onNextSingleDataFunc(outcome)
                }
                is DataOutcome.ListDataState -> {
                    handleUpdateState(outcome.update)
                    onNextListDataFunc(outcome)
                }
                is DataOutcome.PagedListDataState -> {
                    handleUpdateState(outcome.update)
                    onNextPagedListDataFunc(outcome)
                }
                is DataOutcome.ImperativeState -> {
                    handleUpdateState(outcome.update)
                    onNextImperativeState(outcome)
                }
            }
            onNextFunc(outcome)
        }
    }

    private fun handleUpdateState(update: UpdateData?) = update?.let { terminalStateObserver.onUpdateState(it) }
}