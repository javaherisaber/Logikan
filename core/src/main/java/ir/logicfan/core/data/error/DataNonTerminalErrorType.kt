/*
 * Copyright (C) Logicfan, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential
 * Written by Mahdi Javaheri Saber <m.javaherisaber@gmail.com>, Mohammad Hosein Abedini <mohammadhoseinabedini@gmail.com>  2020.
 */

package ir.logicfan.core.data.error

import ir.logicfan.core.data.entity.ErrorData

/**
 * Dictionary of non terminal errors of data layer
 * in Rx world it means that you don't need to tear down your stream
 */
enum class DataNonTerminalErrorType(val code: Int) {
    WRONG_PASSWORD(101),
    WRONG_MOBILE(102);

    companion object {
        fun findNonTerminalExceptionOrNull(errorList: List<ErrorData>): List<DataException.NonTerminal>? {
            val newList = ArrayList<DataException.NonTerminal>()
            for (error in errorList) {
                when (error.code) {
                    WRONG_PASSWORD.code -> newList.add(DataException.NonTerminal.WrongPassword(error.code, error.msg))
                    WRONG_MOBILE.code -> newList.add(DataException.NonTerminal.WrongMobile(error.code, error.msg))
                }
            }
            return if (newList.size > 0) {
                // found some non-terminal errors
                newList
            } else {
                // no non-terminal error found
                null
            }
        }
    }
}