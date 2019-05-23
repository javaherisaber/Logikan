package ir.logicfan.core.data.error

import ir.logicfan.core.data.network.error.NetworkErrorParser
import ir.logicfan.core.data.network.error.NetworkException

/**
 * Gateway to access hierarchy of error in Data layer
 */
object DataErrorParser : ErrorType {

    override fun getErrorType(error: Throwable): DataException = when (error) {
        is NetworkException -> NetworkErrorParser.getErrorType(error)
        else -> DataUnexpectedException()
    }
}
