package ir.logicfan.core.ui.error

/**
 * Determine what to do with exceptions thrown from data layer
 */
interface DataResolution : HttpResolution, NetworkConnectivityResolution {
    /**
     * starting point of error resolution
     */
    fun onResolutionStart(throwable: Throwable)

    /**
     * When there is no caught exception
     */
    fun onDataUnexpectedError(localizedMessage: String, throwable: Throwable)
}