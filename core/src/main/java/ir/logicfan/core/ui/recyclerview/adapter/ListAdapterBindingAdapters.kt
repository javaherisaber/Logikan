package ir.logicfan.core.ui.recyclerview.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

/**
 * Define set of data biding adapters for ListAdapter class
 */
object ListAdapterBindingAdapters {

    /**
     * Add new list of data for inside a given recyclerView
     */
    @BindingAdapter("submitList")
    @JvmStatic
    fun submitList(recyclerView: RecyclerView, newList: List<Nothing>?) {
        val adapter = recyclerView.adapter ?: throw RuntimeException("Adapter not set when binding")
        if (adapter is ListAdapter<*, *>) {
            adapter.submitList(newList)
        } else {
            throw RuntimeException("Adapter is not subtype of ListAdapter")
        }
    }
}