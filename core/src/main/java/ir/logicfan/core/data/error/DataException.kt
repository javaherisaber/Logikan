package ir.logicfan.core.data.error

import com.google.gson.Gson
import ir.logicfan.core.data.entity.ErrorData
import ir.logicfan.core.data.network.base.NetworkApiError
import retrofit2.HttpException
import java.net.HttpURLConnection

/**
 * Data layer exceptions tree
 * single source of truth about all exceptions in this layer
 * all data sources can use this tree
 */
sealed class DataException(val code: Int, val messages: List<String>, val image: String?) : Exception() {
    class Offline : Exception()
    class GenericInput(code: Int, messages: List<String>, image: String?) : DataException(code, messages, image)
    class UnprocessableEntity(code: Int, messages: List<String>, image: String?) : DataException(code, messages, image)
    class BadRequest(code: Int, messages: List<String>, image: String?) : DataException(code, messages, image)
    class UnAuthorized(code: Int, messages: List<String>, image: String?) : DataException(code, messages, image)
    class Forbidden(code: Int, messages: List<String>, image: String?) : DataException(code, messages, image)
    class NotFound(code: Int, messages: List<String>, image: String?) : DataException(code, messages, image)
    class InternalServer(code: Int, messages: List<String>, image: String?) : DataException(code, messages, image)
    class ServiceUnavailable(code: Int, messages: List<String>, image: String?) : DataException(code, messages, image)
    class Timeout(code: Int, messages: List<String>, image: String?) : DataException(code, messages, image)

    companion object {
        fun findExceptionOrNull(throwable: Throwable): Throwable? = try {
            if (throwable is HttpException && supportedErrors.contains(throwable.code())) {
                parseHttpException(throwable)
            } else {
                null
            }
        } catch (ex: Exception) {
            null
        }

        private fun parseHttpException(throwable: HttpException): Throwable? {
            val errorBody = throwable.response().errorBody()
            val response = Gson().fromJson(errorBody?.charStream(), NetworkApiError::class.java)
            return findExceptionOrNull(response.error)
        }

        private fun findExceptionOrNull(errorList: List<ErrorData>): Throwable? {
            var result: DataException? = null
            val messages = mutableListOf<String>()
            for (error in errorList) {
                messages.add(error.msg)
                result = when (error.code) {
                    422 -> UnprocessableEntity(error.code, messages, error.image)
                    HttpURLConnection.HTTP_BAD_REQUEST -> BadRequest(error.code, messages, error.image)
                    HttpURLConnection.HTTP_UNAUTHORIZED -> UnAuthorized(error.code, messages, error.image)
                    HttpURLConnection.HTTP_FORBIDDEN -> Forbidden(error.code, messages, error.image)
                    HttpURLConnection.HTTP_NOT_FOUND -> NotFound(error.code, messages, error.image)
                    HttpURLConnection.HTTP_INTERNAL_ERROR -> InternalServer(error.code, messages, error.image)
                    HttpURLConnection.HTTP_UNAVAILABLE -> ServiceUnavailable(error.code, messages, error.image)
                    HttpURLConnection.HTTP_CLIENT_TIMEOUT -> Timeout(error.code, messages, error.image)
                    HttpURLConnection.HTTP_GATEWAY_TIMEOUT -> Timeout(error.code, messages, error.image)
                    else -> null
                }
            }
            return result
        }

        private val supportedErrors = listOf(
            422,
            HttpURLConnection.HTTP_BAD_REQUEST,
            HttpURLConnection.HTTP_UNAUTHORIZED,
            HttpURLConnection.HTTP_FORBIDDEN,
            HttpURLConnection.HTTP_NOT_FOUND,
            HttpURLConnection.HTTP_INTERNAL_ERROR,
            HttpURLConnection.HTTP_UNAVAILABLE,
            HttpURLConnection.HTTP_CLIENT_TIMEOUT,
            HttpURLConnection.HTTP_GATEWAY_TIMEOUT
        )
    }
}