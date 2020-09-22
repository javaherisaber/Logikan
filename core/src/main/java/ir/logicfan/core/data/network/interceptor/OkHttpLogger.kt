package ir.logicfan.core.data.network.interceptor

import okhttp3.internal.platform.Platform

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