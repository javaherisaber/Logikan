package ir.logicfan.core.data.error

/**
 * To be used in ErrorParser at each data provider (network, db, cache, ...)
 */
interface ErrorType {
    /**
     * get concrete exception of Data layer
     * use smart cast with when expression to resolute which exception is happening
     *
     * @param error captured inside repository error callback
     * @return top level hierarchy of data layer exception to be used in resolution
     */
    fun getErrorType(error: Throwable): DataException
}