package ir.logicfan.core.data.base

import ir.logicfan.core.data.network.base.NetworkApiResponse
import ir.logicfan.core.data.reactive.Transformer

/**
 * Define a utility class to process data layout result in reactive stream
 *
 * @param T type of data to be processed
 * @property mapper map data from network layer to result
 * @property transformer transform stream with context threading (runtime or test contexts)
 */
class DataReactiveUtil<T>(
    val mapper: Mapper<NetworkApiResponse<T>, DataOutcome<T>>,
    val transformer: Transformer<DataOutcome<T>>
)