package ir.logicfan.core.ui.reactive

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 */
typealias MutableOneShotLiveEvent<T> = MutableLiveData<OneShotEvent<T>>
typealias OneShotLiveEvent<T> = LiveData<OneShotEvent<T>>
typealias MutableOneShotUnitLiveEvent = MutableOneShotLiveEvent<Unit>
typealias OneShotUnitLiveEvent = OneShotLiveEvent<Unit>

open class OneShotEvent<out T>(private val content: T) {

    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}