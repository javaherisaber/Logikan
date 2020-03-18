/*
 * Copyright (C) Logicfan, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential
 * Written by Mahdi Javaheri Saber <m.javaherisaber@gmail.com>, Mohammad Hosein Abedini <mohammadhoseinabedini@gmail.com>  2020.
 */

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
     * @property msg message of error received from data source
     */
    sealed class Terminal(val code: Int, val msg: String) : DataException() {
        class Offline : DataException()
        class InternalServer(code: Int, msg: String) : Terminal(code, msg)
        class NotFound(code: Int, msg: String) : Terminal(code, msg)
        class UnAuthorized(code: Int, msg: String) : Terminal(code, msg)
    }

    /**
     * NonTerminal exceptions are those which doesn't terminate operation
     * in Rx world it means that you don't need to tear down your stream
     * can be used to emit warning and input errors
     *
     * @property code number of error defined in DataNonTerminalErrorType
     * @property msg msg message of error received from data source
     */
    sealed class NonTerminal(val code: Int, val msg: String) : DataException() {
        class WrongPassword(code: Int, msg: String) : NonTerminal(code, msg)
        class WrongMobile(code: Int, msg: String) : NonTerminal(code, msg)
    }
}