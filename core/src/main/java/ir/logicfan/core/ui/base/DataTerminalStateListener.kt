package ir.logicfan.core.ui.base

import ir.logicfan.core.data.entity.UpdateData

/**
 * Propagate error from rx callbacks to suitable error resolver
 */
interface DataTerminalStateListener {
    fun onDataTerminalError(throwable: Throwable)
    fun onUpdateState(updateData: UpdateData)
}