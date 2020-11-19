package ir.logicbase.core.ui.util.extension

import androidx.lifecycle.MutableLiveData

/**
 * Append new list with current value of live data and post new value
 */
fun <T> MutableLiveData<List<T>>.appendPost(newList: List<T>) {
    val previous = this.value ?: listOf()
    this.value = previous + newList
}