package ir.logicfan.core.data.mapper

import ir.logicfan.core.data.base.DataOutcome
import ir.logicfan.core.data.base.Mapper
import ir.logicfan.core.data.network.base.NetworkApiResponse

/**
 * Map a NetworkApiResponse object to DataOutcome
 *
 * @param T type of data expected from network
 */
class NetworkApiResponseToDataOutcomeMapper<T> : Mapper<NetworkApiResponse<T>, DataOutcome<T>>() {
    override fun mapFrom(from: NetworkApiResponse<T>): DataOutcome<T> = when {
        from.data != null && from.pagination == null && from.data is List<*> ->
            DataOutcome.ListDataState(from.success, from.data, from.emptyList, from.update)
        from.data == null && from.error == null && from.pagination == null ->
            DataOutcome.ImperativeState(from.success, from.update)
        from.data != null && from.error == null && from.pagination != null ->
            DataOutcome.PagedListDataState(from.success, from.data, from.pagination, from.emptyList, from.update)
        from.data != null && from.pagination == null ->
            DataOutcome.SingleDataState(from.success, from.data, from.emptyList, from.update)
        from.error != null ->
            DataOutcome.ErrorState(from.error, from.update)
        else ->
            // Imperative state (to satisfy when expression exhaustive resolution)
            DataOutcome.ImperativeState(from.success, from.update)
    }
}