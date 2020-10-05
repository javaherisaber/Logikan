package ir.logicfan.core.data.mapper

import ir.logicfan.core.data.base.DataOutcome
import ir.logicfan.core.data.base.Mapper
import ir.logicfan.core.data.network.base.NetworkApiSuccess

/**
 * Map a NetworkApiResponse object to DataOutcome
 *
 * @param T type of data expected from network
 */
class NetworkApiSuccessToDataOutcomeMapper<T> : Mapper<NetworkApiSuccess<T>, DataOutcome<T>>() {
    override fun mapFrom(from: NetworkApiSuccess<T>): DataOutcome<T> = when {
        from.data != null && from.pagination == null && from.data is List<*> ->
            DataOutcome.ListDataState(from.data, from.emptyList, from.success, from.update)
        from.data == null && from.pagination == null ->
            DataOutcome.ImperativeState(from.success, from.update)
        from.data != null && from.pagination != null ->
            DataOutcome.PagedListDataState(from.data, from.emptyList, from.pagination, from.success, from.update)
        from.data != null && from.pagination == null ->
            DataOutcome.SingleDataState(from.data, from.emptyList, from.success, from.update)
        else -> error("Unresolved response payload from remote server")
    }
}