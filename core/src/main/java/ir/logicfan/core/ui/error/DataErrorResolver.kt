package ir.logicfan.core.ui.error

import android.content.Context
import javax.inject.Inject

class DataErrorResolver @Inject constructor(context: Context, private val dataResolution: DataResolution) :
    DataErrorResolutionByCase(context) {
    override fun onDataUnexpectedError(localizedMessage: String, throwable: Throwable) {
        dataResolution.onDataUnexpectedError(localizedMessage, throwable)
    }

    override fun onClientError(localizedMessage: String, throwable: Throwable) {
        dataResolution.onClientError(localizedMessage, throwable)
    }

    override fun onIOError(localizedMessage: String, throwable: Throwable) {
        dataResolution.onIOError(localizedMessage, throwable)
    }

    override fun onServerError(localizedMessage: String, throwable: Throwable) {
        dataResolution.onServerError(localizedMessage, throwable)
    }

    override fun onNoSuchResourceError(localizedMessage: String, throwable: Throwable) {
        dataResolution.onNoSuchResourceError(localizedMessage, throwable)
    }

    override fun onUnauthorizedError(localizedMessage: String, throwable: Throwable) {
        dataResolution.onUnauthorizedError(localizedMessage, throwable)
    }

    override fun onOtherHttpError(throwable: Throwable) {
        dataResolution.onOtherHttpError(throwable)
    }

    override fun onConnectivityAvailable(localizedMessage: String?, throwable: Throwable?) {
        dataResolution.onConnectivityAvailable(localizedMessage, throwable)
    }

    override fun onConnectivityUnavailable(localizedMessage: String, throwable: Throwable) {
        dataResolution.onConnectivityUnavailable(localizedMessage, throwable)
    }
}