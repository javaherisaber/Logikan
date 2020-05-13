package ir.logicfan.core.data.reactive

/**
 * Get notified about error state of observable
 */
interface ErrorStateObserver {
    fun onErrorState(throwable: Throwable)
}