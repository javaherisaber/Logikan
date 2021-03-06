package ir.logicbase.core.data.network.interceptor

import android.content.Context
import ir.logicbase.core.data.error.DataException
import ir.logicbase.core.data.util.Connectivity
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * Define an interceptor to check fo connectivity and throw Offline exception if not connected
 */
class NetworkConnectivityInterceptor @Inject
constructor(private val context: Context) : Interceptor {

    @Throws(DataException.Offline::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!Connectivity.isUserOnline(context)) {
            throw DataException.Offline()
        }
        return chain.proceed(chain.request())
    }
}
