package ir.logicfan.core.ui.reactive

import androidx.lifecycle.Observer

/**
 * An [Observer] for [OneShotEvent]s, simplifying the pattern of checking if the [OneShotEvent]'s content has
 * already been handled.
 *
 * [onEventUnhandledContent] is *only* called if the [OneShotEvent]'s contents has not been handled.
 */
class OneShotEventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<OneShotEvent<T>> {
    override fun onChanged(event: OneShotEvent<T>?) {
        event?.getContentIfNotHandled()?.let { value ->
            onEventUnhandledContent(value)
        }
    }
}