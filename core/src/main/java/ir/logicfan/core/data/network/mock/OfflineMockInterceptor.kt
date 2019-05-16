package ir.logicfan.core.data.network.mock

import okhttp3.*
import java.io.IOException
import javax.inject.Inject

/**
 * provide network response from json mock in local device
 *
 * @property apiEnableMock master setting to enable or disable mock
 * @property apiExcludeFromMock exclude specific endpoint from being mocked
 */
class OfflineMockInterceptor @Inject
constructor(
    private val baseUrl: String,
    private val mockJsonProvider: MockJsonProvider,
    private val apiEnableMock: Boolean,
    private val apiExcludeFromMock: Array<String>
) : Interceptor {

    companion object {
        const val RESPONSE_MEDIA_TYPE = "application/json"
        const val RESPONSE_MESSAGE = "OK"
        const val RESPONSE_CODE = 200
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url().toString()

        val requestPath = url
            .replace(baseUrl, "")  // remove base url
            .replace("\\?.*".toRegex(), "") // remove query parameters

        var excludeRequestFound = false
        // check if requestPath is excluded
        for (item in apiExcludeFromMock) {
            if (item == requestPath) {
                excludeRequestFound = true
            }
        }

        if (!apiEnableMock && !excludeRequestFound) {
            val json = mockJsonProvider.getMockJsonOrNull(requestPath)
            return json?.let {
                // json is found
                val contentType = MediaType.parse(RESPONSE_MEDIA_TYPE)
                val responseBody = ResponseBody.create(contentType, it)
                Response.Builder()
                    .body(responseBody)
                    .request(chain.request()).message(RESPONSE_MESSAGE)
                    .protocol(Protocol.HTTP_1_1)
                    .code(RESPONSE_CODE)
                    .build()
            } ?: run {
                // no json found, proceed request from internet
                chain.proceed(chain.request())
            }
        } else {
          return chain.proceed(chain.request())
        }
    }
}
