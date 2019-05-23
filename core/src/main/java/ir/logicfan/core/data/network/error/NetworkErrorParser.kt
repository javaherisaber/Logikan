package ir.logicfan.core.data.network.error

import android.util.Log
import ir.logicfan.core.data.error.DataException
import ir.logicfan.core.data.error.DataUnexpectedException
import ir.logicfan.core.data.error.ErrorType
import ir.logicfan.core.data.network.error.exception.*
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.HttpURLConnection

/**
 * Gateway to access hierarchy of error in network package
 */
object NetworkErrorParser : ErrorType {
    private const val TAG = "HttpError"

    override fun getErrorType(error: Throwable): DataException = when (error) {
        is NetworkOfflineException -> NetworkOfflineException()
        is HttpException -> getHttpErrorType(error.response())
        is IOException -> NetworkIOException()
        else -> DataUnexpectedException()
    }

    private fun getHttpErrorType(response: Response<*>): DataException {
        Log.d(TAG, response.message())
        return when (response.code()) {
            HttpURLConnection.HTTP_UNAUTHORIZED -> NetworkUnAuthorizedException()
            HttpURLConnection.HTTP_NOT_FOUND or HttpURLConnection.HTTP_MOVED_PERM -> NetworkNoSuchResourceException()
            in 400..499 -> NetworkClientException()
            in 500..599 -> NetworkServerException()
            else -> NetworkOtherHttpException()
        }
    }
}