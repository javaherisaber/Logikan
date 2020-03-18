/*
 * Copyright (C) Logicfan, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential
 * Written by Mahdi Javaheri Saber <m.javaherisaber@gmail.com>, Mohammad Hosein Abedini <mohammadhoseinabedini@gmail.com>  2020.
 */

package ir.logicfan.core.data.base

import ir.logicfan.core.data.entity.ErrorData
import ir.logicfan.core.data.entity.PaginationData

/**
 * Define an outcome from data layer
 * there is only one active State at a given object
 *
 * @param T type of data being used
 */
sealed class DataOutcome<out T> {
    /**
     * When there is no need to have data payload
     * suitable for upload and acknowledge requests
     *
     * @property success whether request was successful or not
     */
    data class ImperativeState(val success: Boolean) : DataOutcome<Nothing>()

    /**
     * When the result of request is only error
     *
     * @property errorList list of errors
     */
    data class ErrorState(val errorList: List<ErrorData>) : DataOutcome<Nothing>()

    /**
     * When the result of request is a single data
     *
     * @param T type of data being emitted
     * @property data data being emitted
     */
    data class SingleDataState<out T>(val success: Boolean, val data: T) : DataOutcome<T>()

    /**
     * When the result of request is a list of data
     *
     * @param T type of data being emitted
     * @property dataList list of data being emitted
     */
    data class ListDataState<out T>(val success: Boolean, val dataList: T) : DataOutcome<T>()

    /**
     * When the result of request is a paged list of data
     *
     * @param T type of data being emitted
     * @property dataList list of data being emitted
     * @property pagination information need to do pagination on next pages
     */
    data class PagedListDataState<out T>(
        val success: Boolean,
        val dataList: T, val pagination: PaginationData
    ) : DataOutcome<T>()
}