package ir.logicbase.core.ui.recyclerview.diffutil

import androidx.recyclerview.widget.DiffUtil
import kotlin.reflect.KProperty1

/**
 * Define a DiffUtil callback which compare two items with one unique property
 * if items unique property are same it compare their content with equals check
 * NOTICE: data class should extend Equatable interface
 *
 * @param T type of data being compared
 * @property uniqueProperty property being compared
 */
class SimpleDiffCallback<T : Equatable>(private val uniqueProperty: KProperty1<T, *>) : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = uniqueProperty.get(oldItem) == uniqueProperty.get(newItem)
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = oldItem == newItem
    override fun getChangePayload(oldItem: T, newItem: T): Any? = newItem
}