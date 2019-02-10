package ir.logicfan.core.data.network.error

import android.util.Log
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.HttpURLConnection.*

object ErrorParser {

    private const val TAG = "HttpError"

    fun getErrorType(error: Throwable): NetworkErrorType {
        return when(error) {
            is UserOfflineException -> NetworkErrorType.NO_INTERNET
            is HttpException -> getHttpErrorType(error.response())
            is IOException -> NetworkErrorType.CONNECTIVITY_ERROR
            else -> NetworkErrorType.UNEXPECTED_ERROR
        }
    }

    private fun getHttpErrorType(response: Response<*>): NetworkErrorType {
        Log.d(TAG, response.message())
        return when(response.code()) {
            HTTP_UNAUTHORIZED -> NetworkErrorType.UNAUTHENTICATED
            HTTP_NOT_FOUND or HTTP_MOVED_PERM -> NetworkErrorType.RESOURCE_NOT_AVAILABLE
            in 400..499 -> NetworkErrorType.CLIENT_ERROR
            in 500..599 -> NetworkErrorType.SERVER_ERROR
            else -> NetworkErrorType.UNEXPECTED_ERROR
        }
    }
}
