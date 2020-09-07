package ir.logicfan.core.data.error

/**
 * Data layer exceptions tree
 * single source of truth about all exceptions in this layer
 * all data sources can use this tree
 */
sealed class DataException : Exception() {
    /**
     * Terminal exceptions are those which terminate the operation
     * in Rx world you may consider this as an error being emitted to onError callback
     *
     * @property code number of error defined in DataTerminalErrorType
     * @property messages message of error received from data source
     */
    sealed class Terminal(val code: Int, val messages: List<String>, val image: String?) : DataException() {
        class Offline : DataException()
        class GenericInput(code: Int, messages: List<String>, image: String?): Terminal(code, messages, image)
        class BadRequest(code: Int, messages: List<String>, image: String?) : Terminal(code, messages, image)
        class UnAuthorized(code: Int, messages: List<String>, image: String?) : Terminal(code, messages, image)
        class Forbidden(code: Int, messages: List<String>, image: String?) : Terminal(code, messages, image)
        class NotFound(code: Int, messages: List<String>, image: String?) : Terminal(code, messages, image)
        class InternalServer(code: Int, messages: List<String>, image: String?) : Terminal(code, messages, image)
        class ServiceUnavailable(code: Int, messages: List<String>, image: String?) : Terminal(code, messages, image)
        class Timeout(code: Int, messages: List<String>, image: String?) : Terminal(code, messages, image)
    }

    /**
     * NonTerminal exceptions are those which doesn't terminate operation
     * in Rx world it means that you don't need to tear down your stream
     * can be used to emit warning and input errors
     *
     * @property code number of error defined in DataNonTerminalErrorType
     * @property msg msg message of error received from data source
     */
    sealed class NonTerminal(val code: Int, val msg: String, val image: String?) : DataException() {
        class WrongPassword(code: Int, msg: String, image: String?) : NonTerminal(code, msg, image)
        class WrongMobile(code: Int, msg: String, image: String?) : NonTerminal(code, msg, image)
    }
}