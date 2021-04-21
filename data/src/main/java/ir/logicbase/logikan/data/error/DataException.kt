package ir.logicbase.logikan.data.error

import com.google.gson.Gson
import ir.logicbase.logikan.data.entity.UpdateData
import ir.logicbase.logikan.data.network.base.NetworkApiError
import ir.logicbase.logikan.data.network.base.NetworkErrorType
import retrofit2.HttpException

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
    class ForcedUpdate(val update: UpdateData) : Exception()

    companion object {
        fun findExceptionOrNull(throwable: Throwable): Throwable? = try {
            if (throwable is HttpException && NetworkErrorType.isSupported(throwable.code())) {
                parseHttpException(throwable)
            } else {
                null
            }
        } catch (ex: Exception) {
            null
        }

        private fun parseHttpException(throwable: HttpException): Throwable? {
            var result: DataException? = null
            val errorBody = throwable.response().errorBody()
            val response = Gson().fromJson(errorBody?.charStream(), NetworkApiError::class.java)
            if (response.error == null) {
                return getDataException(throwable.code(), listOf(), null)
            }
            val messages = mutableListOf<String>()
            for (error in response.error) {
                messages.add(error.msg)
                result = getDataException(error.code, messages, error.image)
            }
            if (result is UnprocessableEntity && result.code == FORCED_UPDATE_CODE && response.update != null) {
                return ForcedUpdate(response.update)
            }
            return result
        }

        private fun getDataException(code: Int, messages: List<String>, image: String?): DataException = when (code) {
            NetworkErrorType.HTTP_BAD_REQUEST.code -> BadRequest(code, messages, image)
            NetworkErrorType.HTTP_UNAUTHORIZED.code -> UnAuthorized(code, messages, image)
            NetworkErrorType.HTTP_FORBIDDEN.code -> Forbidden(code, messages, image)
            NetworkErrorType.HTTP_NOT_FOUND.code -> NotFound(code, messages, image)
            NetworkErrorType.HTTP_INTERNAL_ERROR.code -> InternalServer(code, messages, image)
            NetworkErrorType.HTTP_UNAVAILABLE.code -> ServiceUnavailable(code, messages, image)
            NetworkErrorType.HTTP_CLIENT_TIMEOUT.code -> Timeout(code, messages, image)
            NetworkErrorType.HTTP_GATEWAY_TIMEOUT.code -> Timeout(code, messages, image)
            else -> UnprocessableEntity(code, messages, image)
        }

        private const val FORCED_UPDATE_CODE = 1030
    }
}