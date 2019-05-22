package ir.logicfan.core.data.reactive

import io.reactivex.ObservableTransformer

abstract class Transformer<T> : ObservableTransformer<T, T>