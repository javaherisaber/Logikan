package ir.logicfan.core.ui.error

import android.content.Context
import ir.logicfan.core.data.error.DataErrorParser
import ir.logicfan.core.data.error.DataUnexpectedException
import ir.logicfan.core.data.network.error.NetworkException
import ir.logicfan.core.data.network.error.exception.*

/**
 * Base class to resolute data error
 */
abstract class DataErrorResolutionByCase(private val context: Context) : DataResolution {

    override fun onResolutionStart(throwable: Throwable) =
        when (val exception = DataErrorParser.getErrorType(throwable)) {
            is NetworkException -> resoluteNetworkError(exception)
            else -> onDataUnexpectedError((exception as DataUnexpectedException).getLocalizedMessage(context), exception)
        }

    private fun resoluteNetworkError(exception: NetworkException) {
        when (exception) {
            is NetworkClientException -> onClientError(exception.getLocalizedMessage(context), exception)
            is NetworkIOException -> onIOError(exception.getLocalizedMessage(context), exception)
            is NetworkNoSuchResourceException -> onNoSuchResourceError(exception.getLocalizedMessage(context), exception)
            is NetworkOfflineException -> onConnectivityUnavailable(exception.getLocalizedMessage(context), exception)
            is NetworkOtherHttpException -> onOtherHttpError(exception)
            is NetworkServerException -> onServerError(exception.getLocalizedMessage(context), exception)
            is NetworkUnAuthorizedException -> onUnauthorizedError(exception.getLocalizedMessage(context), exception)
        }
    }
}