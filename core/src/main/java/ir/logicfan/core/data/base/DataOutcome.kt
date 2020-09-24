package ir.logicfan.core.data.base

import io.reactivex.Observable
import ir.logicfan.core.data.entity.EmptyListData
import ir.logicfan.core.data.entity.ErrorData
import ir.logicfan.core.data.entity.PaginationData
import ir.logicfan.core.data.entity.UpdateData

typealias ImperativeDataResult = DataResult<Nothing>
typealias DataResult<S> = Observable<DataOutcome<S>>

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
    data class ImperativeState(val success: Boolean, val update: UpdateData?) : DataOutcome<Nothing>()

    /**
     * When the result of request is only error
     *
     * @property errorList list of errors
     */
    data class ErrorState(val errorList: List<ErrorData>, val update: UpdateData?) : DataOutcome<Nothing>()

    /**
     * When the result of request is a single data
     *
     * @param T type of data being emitted
     * @property data data being emitted
     */
    data class SingleDataState<out T>(val success: Boolean, val data: T, val update: UpdateData?) : DataOutcome<T>()

    /**
     * When the result of request is a list of data
     *
     * @param T type of data being emitted
     * @property data list of data being emitted
     */
    data class ListDataState<out T>(val success: Boolean, val data: T, val update: UpdateData?) : DataOutcome<T>()

    /**
     * When list data is empty and we have a remote emptyList object
     * @property emptyList remote object to display proper artwork and message
     */
    data class EmptyListState<out T>(
        val success: Boolean, val data: T, val emptyList: EmptyListData, val update: UpdateData?
    ) : DataOutcome<T>()

    /**
     * When the result of request is a paged list of data
     *
     * @param T type of data being emitted
     * @property data list of data being emitted
     * @property pagination information need to do pagination on next pages
     */
    data class PagedListDataState<out T>(
        val success: Boolean,
        val data: T, val pagination: PaginationData,
        val update: UpdateData?
    ) : DataOutcome<T>()
}