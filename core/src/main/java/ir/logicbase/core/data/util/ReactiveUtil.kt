package ir.logicbase.core.data.util

import io.reactivex.Observable
import ir.logicbase.core.data.base.DataOutcome
import ir.logicbase.core.data.base.Mapper
import ir.logicbase.core.data.mapper.NetworkApiSuccessToDataOutcomeMapper
import ir.logicbase.core.data.network.base.NetworkApiSuccess
import ir.logicbase.core.data.reactive.ASyncTransformer
import ir.logicbase.core.data.reactive.Transformer

typealias ImperativeReactiveUtil = ReactiveUtil<Nothing>
/**
 * Define a utility class to process data layout result in reactive stream
 *
 * @param T type of data to be processed
 * @property mapper map data from network layer to result
 * @property transformer transform stream with context threading (runtime or test contexts)
 */
class ReactiveUtil<T>(
    private val mapper: Mapper<NetworkApiSuccess<T>, DataOutcome<T>> = NetworkApiSuccessToDataOutcomeMapper(),
    private val transformer: Transformer<DataOutcome<T>> = ASyncTransformer()
) {

    /**
     * Define a function to delegate schedulers to transformer
     * this is a higher order function, which means you can call it like a lambda
     * @param receiver observable being consumed at call site
     */
    fun composeWith(receiver: () -> Observable<DataOutcome<T>>): Observable<DataOutcome<T>> = receiver().compose(transformer)

    fun map(from: NetworkApiSuccess<T>): DataOutcome<T> = mapper.mapFrom(from)
}