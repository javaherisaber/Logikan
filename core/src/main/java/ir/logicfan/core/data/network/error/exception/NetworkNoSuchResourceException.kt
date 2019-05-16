package ir.logicfan.core.data.network.error.exception

import android.content.Context
import ir.logicfan.core.R
import ir.logicfan.core.data.error.LocalizedError
import ir.logicfan.core.data.network.error.NetworkException

/**
 * When we have http exception with code 404 and 301
 */
class NetworkNoSuchResourceException : NetworkException(), LocalizedError {
    override fun getLocalizedMessage(context: Context): String {
        return context.resources.getString(R.string.error_resource_not_available)
    }
}