package ir.logicfan.core.data.reactive

/**
 * Notify observer about state of emit
 */
interface ObservableEmitState : SingleEmitState {
    fun onNextState()
}