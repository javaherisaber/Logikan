package ir.logicfan.core.data.network.error.exception

import android.content.Context
import ir.logicfan.core.R
import ir.logicfan.core.data.error.DataException
import ir.logicfan.core.data.error.LocalizedError

/**
 * When we have http exception in range 500 - 599 response code
 */
class NetworkServerException : DataException(), LocalizedError {
    override fun getLocalizedMessage(context: Context): String {
        return context.resources.getString(R.string.error_server_error)
    }
}