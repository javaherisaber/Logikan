/*
 * Copyright (C) Logicfan, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential
 * Written by Mahdi Javaheri Saber <m.javaherisaber@gmail.com>, Mohammad Hosein Abedini <mohammadhoseinabedini@gmail.com>  2020.
 */

package ir.logicfan.core.data.error

import ir.logicfan.core.data.entity.ErrorData
import java.net.HttpURLConnection

/**
 * Dictionary of terminal errors of data layer
 * in Rx world you may consider this as an error being emitted to onError callback
 */
enum class DataTerminalErrorType(val code: Int) {
    INTERNAL_ERROR(HttpURLConnection.HTTP_INTERNAL_ERROR),
    UNAUTHORIZED(HttpURLConnection.HTTP_UNAUTHORIZED),
    NOT_FOUND(HttpURLConnection.HTTP_NOT_FOUND);

    companion object {
        fun findTerminalExceptionOrNull(errorList: List<ErrorData>): DataException.Terminal? {
            for (error in errorList) {
                when (error.code) {
                    INTERNAL_ERROR.code -> DataException.Terminal.InternalServer(error.code, error.msg)
                    UNAUTHORIZED.code -> DataException.Terminal.UnAuthorized(error.code, error.msg)
                    NOT_FOUND.code -> DataException.Terminal.NotFound(error.code, error.msg)
                }
            }
            return null
        }
    }
}