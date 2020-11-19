package ir.logicbase.core.data.base

import io.reactivex.Observable
import ir.logicbase.core.data.entity.EmptyListData
import ir.logicbase.core.data.entity.PaginationData
import ir.logicbase.core.data.entity.UpdateData

typealias ImperativeDataResult = DataResult<Nothing>
typealias DataResult<S> = Observable<DataOutcome<S>>

/**
 * Define an outcome from data layer
 * there is only one active State at a given object
 *
 * @param T type of data being used
 */
sealed class DataOutcome<out T>(val success: Boolean, val update: UpdateData?) {
    /**
     * When there is no need to have data payload
     * suitable for upload and acknowledge requests
     *
     * @property success whether request was successful or not
     */
    class ImperativeState(success: Boolean, update: UpdateData?) : DataOutcome<Nothing>(success, update)

    /**
     * When the result of request is a single data
     *
     * @param T type of data being emitted
     * @property data data being emitted
     */
    class SingleDataState<out T>(
        val data: T,
        val emptyList: EmptyListData?,
        success: Boolean,
        update: UpdateData?
    ) : DataOutcome<T>(success, update)

    /**
     * When the result of request is a list of data
     *
     * @param T type of data being emitted
     * @property data list of data being emitted
     */
    class ListDataState<out T>(
        val data: T,
        val emptyList: EmptyListData?,
        success: Boolean,
        update: UpdateData?
    ) : DataOutcome<T>(success, update)

    /**
     * When the result of request is a paged list of data
     *
     * @param T type of data being emitted
     * @property data list of data being emitted
     * @property pagination information need to do pagination on next pages
     */
    class PagedListDataState<out T>(
        val data: T,
        val emptyList: EmptyListData?,
        val pagination: PaginationData,
        success: Boolean,
        update: UpdateData?
    ) : DataOutcome<T>(success, update)
}