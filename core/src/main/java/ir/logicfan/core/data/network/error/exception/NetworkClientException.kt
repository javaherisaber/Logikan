package ir.logicfan.core.data.network.error.exception

import android.content.Context
import ir.logicfan.core.R
import ir.logicfan.core.data.error.LocalizedError
import ir.logicfan.core.data.network.error.NetworkException

/**
 * When we have http exception in range 400 - 499 response code
 */
class NetworkClientException : NetworkException(), LocalizedError {
    override fun getLocalizedMessage(context: Context): String =
        context.resources.getString(R.string.error_oops_something_went_wrong)
}