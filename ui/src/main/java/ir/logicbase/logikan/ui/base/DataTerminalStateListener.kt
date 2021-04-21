package ir.logicbase.logikan.ui.base

import ir.logicbase.logikan.data.entity.UpdateData

/**
 * Propagate error from rx callbacks to suitable error resolver
 */
interface DataTerminalStateListener {
    fun onDataTerminalError(throwable: Throwable)
    fun onUpdateState(updateData: UpdateData)
}