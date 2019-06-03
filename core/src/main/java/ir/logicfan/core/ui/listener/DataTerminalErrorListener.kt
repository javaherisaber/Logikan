package ir.logicfan.core.ui.listener

/**
 * Propagate error from rx callbacks to suitable error resolver
 */
interface DataTerminalErrorListener {
    fun onDataTerminalError(throwable: Throwable)
}