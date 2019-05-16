package ir.logicfan.core.data.error

import android.content.Context

/**
 * A message to be displayed on the screen
 */
interface LocalizedError {
    fun getLocalizedMessage(context: Context): String
}
