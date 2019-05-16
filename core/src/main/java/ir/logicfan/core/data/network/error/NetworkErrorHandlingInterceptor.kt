package ir.logicfan.core.data.network.error

import android.content.Context
import ir.logicfan.core.data.network.error.exception.NetworkIOException
import ir.logicfan.core.data.network.error.exception.NetworkOfflineException
import ir.logicfan.core.util.Connectivity
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NetworkErrorHandlingInterceptor @Inject
constructor(private val context: Context) : Interceptor {

    @Throws(NetworkIOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!Connectivity.isUserOnline(context)) {
            throw NetworkOfflineException()
        }
        return chain.proceed(chain.request())
    }
}
