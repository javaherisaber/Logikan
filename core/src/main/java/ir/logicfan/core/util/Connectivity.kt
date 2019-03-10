package ir.logicfan.core.util

import android.content.Context
import android.net.ConnectivityManager

/**
 * Check device's network connectivity
 */
object Connectivity {

    fun isUserOnline(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo.isConnected
    }
}