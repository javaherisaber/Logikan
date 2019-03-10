package ir.logicfan.core.data.network.error

import android.content.Context
import ir.logicfan.core.R


enum class NetworkErrorType : NetworkError {

    CONNECTIVITY_ERROR {
        override fun getLocalizedMessage(context: Context): String {
            return context.resources.getString(R.string.error_connectivity_error)
        }
    },
    NO_INTERNET {
        override fun getLocalizedMessage(context: Context): String {
            return context.resources.getString(R.string.error_check_your_internet_connection)
        }
    },
    RESOURCE_NOT_AVAILABLE {
        override fun getLocalizedMessage(context: Context): String {
            return context.resources.getString(R.string.error_resource_not_available)
        }
    },
    SERVER_ERROR {
        override fun getLocalizedMessage(context: Context): String {
            return context.resources.getString(R.string.error_server_error)
        }
    },
    CLIENT_ERROR {
        override fun getLocalizedMessage(context: Context): String {
            return context.resources.getString(R.string.error_oops_something_went_wrong)
        }
    },
    UNEXPECTED_ERROR {
        override fun getLocalizedMessage(context: Context): String {
            return context.resources.getString(R.string.error_something_went_wrong)
        }
    },
    UNAUTHENTICATED {
        override fun getLocalizedMessage(context: Context): String {
            return context.resources.getString(R.string.error_un_authorized_message)
        }
    }
}
