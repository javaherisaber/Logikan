package ir.logicfan.core.data.network.interceptor

import android.content.Context
import ir.logicfan.core.di.qulifier.ApplicationType
import ir.logicfan.core.ui.util.ApplicationUtils
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * Define an interceptor to send application attributes as header
 * (to distinguish between applications on the same server)
 */
class ApplicationInfoInterceptor @Inject
constructor(
    private val context: Context,
    @ApplicationType private val applicationType: String
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Application-Type", applicationType)
            .addHeader("Application-Package", ApplicationUtils.getPackageName(context))
            .addHeader("Application-VersionCode", ApplicationUtils.getApkVersionCode(context))
            .addHeader("Application-VersionName", ApplicationUtils.getApkVersionName(context))
            .build()
        return chain.proceed(request)
    }
}