package ir.logicbase.core.data.network.base

import java.net.HttpURLConnection

enum class NetworkErrorType(val code: Int) {
    HTTP_BAD_REQUEST(HttpURLConnection.HTTP_BAD_REQUEST),
    HTTP_UNAUTHORIZED(HttpURLConnection.HTTP_UNAUTHORIZED),
    HTTP_FORBIDDEN(HttpURLConnection.HTTP_FORBIDDEN),
    HTTP_NOT_FOUND(HttpURLConnection.HTTP_NOT_FOUND),
    HTTP_INTERNAL_ERROR(HttpURLConnection.HTTP_INTERNAL_ERROR),
    HTTP_UNAVAILABLE(HttpURLConnection.HTTP_UNAVAILABLE),
    HTTP_CLIENT_TIMEOUT(HttpURLConnection.HTTP_CLIENT_TIMEOUT),
    HTTP_GATEWAY_TIMEOUT(HttpURLConnection.HTTP_GATEWAY_TIMEOUT),
    HTTP_UNPROCESSABLE_ENTITY(422),
    HTTP_TOO_MANY_REQUEST(429);

    companion object {
        fun isSupported(code: Int): Boolean = values().firstOrNull { it.code == code } != null
    }
}