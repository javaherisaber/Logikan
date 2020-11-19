package ir.logicbase.core.data.reactive

import io.reactivex.ObservableTransformer

/**
 * Let you get rid of defining T value two times
 */
abstract class Transformer<T> : ObservableTransformer<T, T>