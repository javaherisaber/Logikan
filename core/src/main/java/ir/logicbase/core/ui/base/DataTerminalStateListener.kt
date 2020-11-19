package ir.logicbase.core.ui.base

import ir.logicbase.core.data.entity.UpdateData

/**
 * Propagate error from rx callbacks to suitable error resolver
 */
interface DataTerminalStateListener {
    fun onDataTerminalError(throwable: Throwable)
    fun onUpdateState(updateData: UpdateData)
}