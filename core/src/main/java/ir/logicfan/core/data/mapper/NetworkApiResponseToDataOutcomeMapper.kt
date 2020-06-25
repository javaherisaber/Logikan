package ir.logicfan.core.data.mapper

import ir.logicfan.core.data.base.DataOutcome
import ir.logicfan.core.data.base.Mapper
import ir.logicfan.core.data.network.base.NetworkApiResponse

/**
 * Map a NetworkApiResponse object to DataOutcome
 *
 * @param T type of data expected from network
 */
// todo: add pagination when ready
class NetworkApiResponseToDataOutcomeMapper<T> : Mapper<NetworkApiResponse<T>, DataOutcome<T>>() {
    override fun mapFrom(from: NetworkApiResponse<T>): DataOutcome<T> = when {
//        from.data != null && from.pagination == null && from.data is List<*> ->
        from.data != null && from.data is List<*> ->
            // List data state
            DataOutcome.ListDataState(from.success, from.data, from.update)
//        from.data == null && from.error == null && from.pagination == null ->
        from.data == null && from.error == null ->
            // Imperative state
            DataOutcome.ImperativeState(from.success, from.update)
//        from.data != null && from.pagination == null ->
        from.data != null ->
            // Single data state
            DataOutcome.SingleDataState(from.success, from.data, from.update)
//        from.data != null && from.pagination != null && from.data is List<*> ->
//            // PagedList data state
//            DataOutcome.PagedListDataState(from.success, from.data, from.pagination)
        from.error != null ->
            // Error state
            DataOutcome.ErrorState(from.error, from.update)
        else ->
            // Imperative state (to satisfy when expression exhaustive resolution)
            DataOutcome.ImperativeState(from.success, from.update)
    }
}