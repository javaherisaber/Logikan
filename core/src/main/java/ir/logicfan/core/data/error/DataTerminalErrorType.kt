package ir.logicfan.core.data.error

import com.google.gson.Gson
import ir.logicfan.core.data.entity.ErrorData
import ir.logicfan.core.data.network.base.NetworkApiResponse
import retrofit2.HttpException
import java.lang.Exception
import java.net.HttpURLConnection

/**
 * Dictionary of terminal errors of data layer
 * in Rx world you may consider this as an error being emitted to onError callback
 */
enum class DataTerminalErrorType(val code: Int) {
    BAD_REQUEST(HttpURLConnection.HTTP_BAD_REQUEST),
    UNAUTHORIZED(HttpURLConnection.HTTP_UNAUTHORIZED),
    FORBIDDEN(HttpURLConnection.HTTP_FORBIDDEN),
    NOT_FOUND(HttpURLConnection.HTTP_NOT_FOUND),
    INTERNAL_SERVER(HttpURLConnection.HTTP_INTERNAL_ERROR),
    SERVICE_UNAVAILABLE(HttpURLConnection.HTTP_UNAVAILABLE),
    TIMEOUT(HttpURLConnection.HTTP_CLIENT_TIMEOUT);

    companion object {
        fun findTerminalExceptionOrNull(errorList: List<ErrorData>): DataException.Terminal? {
            var result: DataException.Terminal? = null
            for (error in errorList) {
                result = when (error.code) {
                    BAD_REQUEST.code -> DataException.Terminal.BadRequest(error.code, error.msg)
                    UNAUTHORIZED.code -> DataException.Terminal.UnAuthorized(error.code, error.msg)
                    FORBIDDEN.code -> DataException.Terminal.Forbidden(error.code, error.msg)
                    NOT_FOUND.code -> DataException.Terminal.NotFound(error.code, error.msg)
                    INTERNAL_SERVER.code -> DataException.Terminal.InternalServer(error.code, error.msg)
                    SERVICE_UNAVAILABLE.code -> DataException.Terminal.ServiceUnavailable(error.code, error.msg)
                    TIMEOUT.code -> DataException.Terminal.Timeout(error.code, error.msg)
                    else -> null
                }
            }
            return result
        }

        fun isTerminal(code: Int): Boolean {
            for (item in values()) {
                if (item.code == code) {
                    return true
                }
            }
            return false
        }

        fun findTerminalExceptionOrNull(throwable: Throwable): DataException.Terminal? = try {
            if (throwable is HttpException && isTerminal(throwable.code())) {
                val errorBody = throwable.response().errorBody()
                val response = Gson().fromJson(errorBody?.charStream(), NetworkApiResponse::class.java)
                response.error?.let { findTerminalExceptionOrNull(it) }
            } else {
                null
            }
        } catch (ex: Exception) {
            null
        }
    }
}