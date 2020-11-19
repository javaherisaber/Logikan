package ir.logicbase.core.data.network.interceptor

import ir.logicbase.core.util.extension.decodeUnicode
import okhttp3.*
import okhttp3.internal.http.HttpHeaders
import okhttp3.internal.platform.Platform
import okio.Buffer
import java.io.EOFException
import java.io.IOException
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit

/**
 * An OkHttp interceptor which logs request and response information. Can be applied as an
 * [application interceptor][OkHttpClient.interceptors] or as a [ ][OkHttpClient.networkInterceptors].
 *
 * The format of the logs created by
 * this class should not be considered stable and may change slightly between releases. If you need
 * a stable logging format, use your own interceptor.
 */
class Utf8HttpLoggingInterceptor @JvmOverloads constructor(private val logger: OkHttpLogger = OkHttpLogger.DEFAULT) : Interceptor {
    @Suppress("unused")
    enum class Level {
        /** No logs.  */
        NONE,

        /**
         * Logs request and response lines.
         *
         *
         * Example:
         * <pre>`--> POST /greeting http/1.1 (3-byte body)
         *
         * <-- 200 OK (22ms, 6-byte body)
        `</pre> *
         */
        BASIC,

        /**
         * Logs request and response lines and their respective headers.
         *
         *
         * Example:
         * <pre>`--> POST /greeting http/1.1
         * Host: example.com
         * Content-Type: plain/text
         * Content-Length: 3
         * --> END POST
         *
         * <-- 200 OK (22ms)
         * Content-Type: plain/text
         * Content-Length: 6
         * <-- END HTTP
        `</pre> *
         */
        HEADERS,

        /**
         * Logs request and response lines and their respective headers and bodies (if present).
         *
         *
         * Example:
         * <pre>`--> POST /greeting http/1.1
         * Host: example.com
         * Content-Type: plain/text
         * Content-Length: 3
         *
         * Hi?
         * --> END POST
         *
         * <-- 200 OK (22ms)
         * Content-Type: plain/text
         * Content-Length: 6
         *
         * Hello!
         * <-- END HTTP
        `</pre> *
         */
        BODY
    }


    @Volatile
    var level = Level.NONE
        private set

    /** Change the level at which this interceptor logs.  */
    fun setLevel(level: Level): Utf8HttpLoggingInterceptor {
        this.level = level
        return this
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val level = level
        val request = chain.request()
        if (level == Level.NONE) {
            return chain.proceed(request)
        }
        val logBody = level == Level.BODY
        val logHeaders = logBody || level == Level.HEADERS
        val requestBody = request.body()
        val hasRequestBody = requestBody != null
        val connection = chain.connection()
        val protocol = if (connection != null) connection.protocol() else Protocol.HTTP_1_1
        var requestStartMessage: String = "--> " + request.method() + ' ' + request.url() + ' ' + protocol
        if (!logHeaders && hasRequestBody) {
            requestStartMessage += " (" + requestBody!!.contentLength() + "-byte body)"
        }
        logger.log(requestStartMessage)
        if (logHeaders) {
            if (hasRequestBody) {
                // Request body headers are only present when installed as a network interceptor. Force
                // them to be included (when available) so there values are known.
                if (requestBody!!.contentType() != null) {
                    logger.log("Content-Type: " + requestBody.contentType())
                }
                if (requestBody.contentLength() != -1L) {
                    logger.log("Content-Length: " + requestBody.contentLength())
                }
            }
            val headers = request.headers()
            var i = 0
            val count = headers.size()
            while (i < count) {
                val name = headers.name(i)
                // Skip headers from the request body as they are explicitly logged above.
                if (!"Content-Type".equals(name, ignoreCase = true) && !"Content-Length".equals(
                        name,
                        ignoreCase = true
                    )
                ) {
                    logger.log(name + ": " + headers.value(i))
                }
                i++
            }
            if (!logBody || !hasRequestBody) {
                logger.log("--> END " + request.method())
            } else if (bodyEncoded(request.headers())) {
                logger.log("--> END " + request.method() + " (encoded body omitted)")
            } else {
                val buffer = Buffer()
                requestBody!!.writeTo(buffer)
                var charset: Charset? = UTF8
                val contentType = requestBody.contentType()
                if (contentType != null) {
                    charset = contentType.charset(UTF8)
                }
                logger.log("")
                if (isPlaintext(buffer)) {
                    logger.log(buffer.readString(charset ?: UTF8))
                    logger.log(
                        "--> END " + request.method()
                                + " (" + requestBody.contentLength() + "-byte body)"
                    )
                } else {
                    logger.log(
                        ("--> END " + request.method() + " (binary "
                                + requestBody.contentLength() + "-byte body omitted)")
                    )
                }
            }
        }
        val startNs = System.nanoTime()
        val response: Response
        try {
            response = chain.proceed(request)
        } catch (e: Exception) {
            logger.log("<-- HTTP FAILED: $e")
            throw e
        }
        val tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs)
        val responseBody = response.body()
        val contentLength = responseBody!!.contentLength()
        val bodySize = if (contentLength != -1L) "$contentLength-byte" else "unknown-length"
        logger.log(
            ("<-- " + response.code() + ' ' + response.message() + ' '
                    + response.request().url() + " (" + tookMs + "ms" + (if (!logHeaders) (", "
                    + bodySize + " body") else "") + ')')
        )
        if (logHeaders) {
            val headers = response.headers()
            var i = 0
            val count = headers.size()
            while (i < count) {
                logger.log(headers.name(i) + ": " + headers.value(i))
                i++
            }
            if (!logBody || !HttpHeaders.hasBody(response)) {
                logger.log("<-- END HTTP")
            } else if (bodyEncoded(response.headers())) {
                logger.log("<-- END HTTP (encoded body omitted)")
            } else {
                val source = responseBody.source()
                source.request(Long.MAX_VALUE) // Buffer the entire body.
                val buffer = source.buffer()
                var charset: Charset? = UTF8
                val contentType = responseBody.contentType()
                if (contentType != null) {
                    charset = contentType.charset(UTF8)
                }
                if (!isPlaintext(buffer)) {
                    logger.log("")
                    logger.log("<-- END HTTP (binary " + buffer.size() + "-byte body omitted)")
                    return response
                }
                if (contentLength != 0L) {
                    logger.log("")
                    val body = buffer.clone().readString(charset ?: UTF8)
                    logger.log(body.decodeUnicode())
                }
                logger.log("<-- END HTTP (" + buffer.size() + "-byte body)")
            }
        }
        return response
    }

    private fun bodyEncoded(headers: Headers): Boolean {
        val contentEncoding = headers["Content-Encoding"]
        return contentEncoding != null && !contentEncoding.equals("identity", ignoreCase = true)
    }

    companion object {
        private val UTF8 = Charset.forName("UTF-8")

        /**
         * Returns true if the body in question probably contains human readable text. Uses a small sample
         * of code points to detect unicode control characters commonly used in binary file signatures.
         */
        fun isPlaintext(buffer: Buffer): Boolean {
            try {
                val prefix = Buffer()
                val byteCount = if (buffer.size() < 64) buffer.size() else 64
                buffer.copyTo(prefix, 0, byteCount)
                for (i in 0..15) {
                    if (prefix.exhausted()) {
                        break
                    }
                    val codePoint = prefix.readUtf8CodePoint()
                    if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                        return false
                    }
                }
                return true
            } catch (e: EOFException) {
                return false // Truncated UTF-8 sequence.
            }
        }
    }

    interface OkHttpLogger {
        fun log(message: String?)

        companion object {
            /** A [OkHttpLogger] defaults output appropriate for the current platform.  */
            val DEFAULT: OkHttpLogger = object : OkHttpLogger {
                override fun log(message: String?) {
                    Platform.get().log(Platform.INFO, message, null)
                }
            }
        }
    }
}