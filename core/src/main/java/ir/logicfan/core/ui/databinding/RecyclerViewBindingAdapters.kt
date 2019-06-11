package ir.logicfan.core.ui.databinding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

object RecyclerViewBindingAdapters {

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