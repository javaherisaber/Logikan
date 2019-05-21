package ir.logicfan.core.ui.error

interface HttpResolution {
    fun onClientError(localizedMessage: String, throwable: Throwable)
    fun onIOError(localizedMessage: String, throwable: Throwable)
    fun onServerError(localizedMessage: String, throwable: Throwable)
    fun onNoSuchResourceError(localizedMessage: String, throwable: Throwable)
    fun onUnauthorizedError(localizedMessage: String, throwable: Throwable)
    fun onOtherHttpError(throwable: Throwable)
}