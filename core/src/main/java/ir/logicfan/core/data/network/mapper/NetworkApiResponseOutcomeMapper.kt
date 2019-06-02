package ir.logicfan.core.data.network.mapper

import ir.logicfan.core.data.base.DataOutcome
import ir.logicfan.core.data.network.base.NetworkApiResponse
import ir.logicfan.core.data.reactive.Mapper

/**
 * Map a NetworkApiResponse object to DataOutcome
 *
 * @param T type of data expected from network
 */
class NetworkApiResponseOutcomeMapper<T> : Mapper<NetworkApiResponse<T>, DataOutcome<T>>() {
    override fun mapFrom(from: NetworkApiResponse<T>): DataOutcome<T> {
        return when {
            from.data == null && from.error == null && from.pagination == null ->
                // Imperative state
                DataOutcome.ImperativeState(from.success)
            from.error != null ->
                // Error state
                DataOutcome.ErrorState(from.error)
            from.data != null && from.pagination == null ->
                // Single data state
                DataOutcome.SingleDataState(from.data)
            from.data != null && from.pagination != null ->
                // List data state
                DataOutcome.ListDataState(from.data, from.pagination)
            else ->
                // Imperative state (to satisfy when expression exhaustive resolution)
                DataOutcome.ImperativeState(from.success)
        }
    }
}