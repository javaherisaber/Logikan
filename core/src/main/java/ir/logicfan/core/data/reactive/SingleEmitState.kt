package ir.logicfan.core.data.reactive

/**
 * Notify observer about state of emit
 */
interface SingleEmitState {
    fun onErrorState(throwable: Throwable?)
    fun onSuccessState()
}