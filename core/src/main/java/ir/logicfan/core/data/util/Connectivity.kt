package ir.logicfan.core.data.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * Check device's network connectivity
 */
object Connectivity {

    @JvmStatic
    fun isUserOnline(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
        return netInfo?.isConnected ?: false
    }
}