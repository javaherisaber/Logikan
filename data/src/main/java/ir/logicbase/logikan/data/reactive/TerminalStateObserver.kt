package ir.logicbase.logikan.data.reactive

import ir.logicbase.logikan.data.entity.UpdateData

/**
 * Get notified about error state of observable
 */
interface TerminalStateObserver {
    fun onErrorState(throwable: Throwable)
    fun onUpdateState(update: UpdateData)
}