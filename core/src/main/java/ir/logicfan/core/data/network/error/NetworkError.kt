package ir.logicfan.core.data.network.error

import android.content.Context

interface NetworkError {
    fun getLocalizedMessage(context: Context): String
}
