package ir.logicfan.core.data.network.error

import android.content.Context
import ir.logicfan.core.util.Connectivity
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class ErrorHandlingInterceptor @Inject
constructor(private val context: Context) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!Connectivity.isUserOnline(context)) {
            throw UserOfflineException()
        }
        return chain.proceed(chain.request())
    }
}
