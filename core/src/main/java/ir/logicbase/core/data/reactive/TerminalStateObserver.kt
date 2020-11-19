package ir.logicbase.core.data.reactive

import ir.logicbase.core.data.entity.UpdateData

/**
 * Get notified about error state of observable
 */
interface TerminalStateObserver {
    fun onErrorState(throwable: Throwable)
    fun onUpdateState(update: UpdateData)
}