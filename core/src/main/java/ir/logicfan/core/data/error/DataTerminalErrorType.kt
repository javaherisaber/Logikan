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
    GENERIC_INPUT(100),
    UNPROCESSABLE_ENTITY(422),
    BAD_REQUEST(HttpURLConnection.HTTP_BAD_REQUEST),
    UNAUTHORIZED(HttpURLConnection.HTTP_UNAUTHORIZED),
    FORBIDDEN(HttpURLConnection.HTTP_FORBIDDEN),
    NOT_FOUND(HttpURLConnection.HTTP_NOT_FOUND),
    INTERNAL_SERVER(HttpURLConnection.HTTP_INTERNAL_ERROR),
    SERVICE_UNAVAILABLE(HttpURLConnection.HTTP_UNAVAILABLE),
    GATEWAY_TIMEOUT(HttpURLConnection.HTTP_GATEWAY_TIMEOUT),
    CLIENT_TIMEOUT(HttpURLConnection.HTTP_CLIENT_TIMEOUT);

    companion object {
        fun findTerminalExceptionOrNull(errorList: List<ErrorData>): DataException.Terminal? {
            var result: DataException.Terminal? = null
            val messages = mutableListOf<String>()
            for (error in errorList) {
                messages.add(error.msg)
                result = when (error.code) {
                    GENERIC_INPUT.code -> DataException.Terminal.GenericInput(error.code, messages, error.image)
                    BAD_REQUEST.code -> DataException.Terminal.BadRequest(error.code, messages, error.image)
                    UNAUTHORIZED.code -> DataException.Terminal.UnAuthorized(error.code, messages, error.image)
                    FORBIDDEN.code -> DataException.Terminal.Forbidden(error.code, messages, error.image)
                    NOT_FOUND.code -> DataException.Terminal.NotFound(error.code, messages, error.image)
                    INTERNAL_SERVER.code -> DataException.Terminal.InternalServer(error.code, messages, error.image)
                    SERVICE_UNAVAILABLE.code -> {
                        DataException.Terminal.ServiceUnavailable(error.code, messages, error.image)
                    }
                    CLIENT_TIMEOUT.code -> DataException.Terminal.Timeout(error.code, messages, error.image)
                    GATEWAY_TIMEOUT.code -> DataException.Terminal.Timeout(error.code, messages, error.image)
                    else -> null
                }
            }
            return result
        }

        fun findTerminalExceptionOrNull(throwable: Throwable): DataException.Terminal? = try {
            if (throwable is HttpException && isTerminal(throwable.code())) {
                parseHttpException(throwable)
            } else {
                null
            }
        } catch (ex: Exception) {
            null
        }

        private fun isTerminal(code: Int): Boolean {
            for (item in values()) {
                if (item.code == code) {
                    return true
                }
            }
            return false
        }

        private fun parseHttpException(throwable: HttpException): DataException.Terminal? {
            val errorBody = throwable.response().errorBody()
            val response = Gson().fromJson(errorBody?.charStream(), NetworkApiResponse::class.java)
            val errors = response.error ?: return null
            if (throwable.code() == UNPROCESSABLE_ENTITY.code && errors.isNotEmpty()) {
                val firstError = errors.first() // we support only one error for unprocessable entity
                return DataException.Terminal.UnprocessableEntity(
                    firstError.code, listOf(firstError.msg), firstError.image
                )
            }
            return findTerminalExceptionOrNull(errors)
        }
    }
}