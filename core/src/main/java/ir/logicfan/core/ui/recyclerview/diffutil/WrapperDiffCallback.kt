package ir.logicfan.core.ui.recyclerview.diffutil

import androidx.recyclerview.widget.DiffUtil
import kotlin.reflect.KProperty1

/**
 * Define a DiffUtil callback to be used for Wrapper items
 *
 * @param T type of item
 * @param S type of state property
 * @param U type of unique property
 */
open class WrapperDiffCallback<T, S : Equatable, U : Equatable>(
    private val dataProperty: KProperty1<T, U>,
    private val stateProperty: KProperty1<T, S>,
    private val uniqueProperty: KProperty1<U, *>
) : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(
        oldItem: T, newItem: T
    ): Boolean {
        val oldData = dataProperty.get(oldItem)
        val newData = dataProperty.get(newItem)
        val oldUnique = uniqueProperty.get(oldData)
        val newUnique = uniqueProperty.get(newData)
        return (oldUnique == newUnique)
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        val oldData = dataProperty.get(oldItem)
        val newData = dataProperty.get(newItem)
        val oldState = stateProperty.get(oldItem)
        val newState = stateProperty.get(newItem)
        return (oldData == newData) && (oldState == newState)
    }

    /**
     * Get's called when areContentsTheSame return false
     *
     * @return data that will be consumed in onBindViewHolder(holder, position, payloads) callback
     */
    override fun getChangePayload(oldItem: T, newItem: T): Any? {
        val oldData = dataProperty.get(oldItem)
        val newData = dataProperty.get(newItem)
        val oldState = stateProperty.get(oldItem)
        val newState = stateProperty.get(newItem)
        return when {
            oldData != newData && oldState != newState -> newItem
            oldData != newData -> newData
            oldState != newState -> newState
            else -> null
        }
    }
}