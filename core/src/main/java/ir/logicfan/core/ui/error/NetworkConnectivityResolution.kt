package ir.logicfan.core.ui.error

interface NetworkConnectivityResolution {
    fun onConnectivityAvailable(localizedMessage: String?, throwable: Throwable?)
    fun onConnectivityUnavailable(localizedMessage: String, throwable: Throwable)
}