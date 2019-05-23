package ir.logicfan.core.data.error

import android.content.Context
import ir.logicfan.core.R

/**
 * When there is no other exception type in data layer causes the issue
 * At development phase you can throw a runtime exception inside your resolution class to find out what is the cause
 */
class DataUnexpectedException : DataException(), LocalizedError {
    override fun getLocalizedMessage(context: Context): String =
        context.resources.getString(R.string.error_something_went_wrong)
}