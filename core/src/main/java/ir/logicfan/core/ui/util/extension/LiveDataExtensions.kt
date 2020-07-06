package ir.logicfan.core.ui.util.extension

import androidx.lifecycle.MutableLiveData
import ir.logicfan.core.ui.reactive.OneShotEvent

/**
 * Post a OneShotEvent of type Unit
 */
fun MutableLiveData<OneShotEvent<Unit>>.postOneShotUnit() {
    this.value = OneShotEvent(Unit)
}

/**
 * Post a OneShotEvent of type T
 */
fun <T> MutableLiveData<OneShotEvent<T>>.postOneShot(value: T) {
    this.value = OneShotEvent(value)
}

/**
 * Append new list with current value of live data and post new value
 */
fun <T> MutableLiveData<List<T>>.appendPost(newList: List<T>) {
    val previous = this.value ?: listOf()
    this.value = previous + newList
}