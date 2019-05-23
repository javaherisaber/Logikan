package ir.logicfan.core.data.network.error.exception

import android.content.Context
import ir.logicfan.core.R
import ir.logicfan.core.data.error.LocalizedError
import ir.logicfan.core.data.network.error.NetworkException

class NetworkIOException : NetworkException(), LocalizedError {
    override fun getLocalizedMessage(context: Context): String =
        context.resources.getString(R.string.error_connectivity_error)
}