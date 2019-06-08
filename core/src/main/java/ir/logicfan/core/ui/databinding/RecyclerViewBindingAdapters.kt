package ir.logicfan.core.ui.databinding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import ir.logicfan.core.ui.adapter.SingleDataAdapter
import ir.logicfan.core.ui.adapter.replaceDataSource

object RecyclerViewBindingAdapters {

    @BindingAdapter("replaceAdapterDataSource")
    @JvmStatic
    fun replaceAdapterDataSource(recyclerView: RecyclerView, dataSource: List<Nothing>?) {
        val adapter = recyclerView.adapter ?: throw RuntimeException("Adapter not set when binding")
        if (adapter is SingleDataAdapter<*>) {
            adapter.replaceDataSource(dataSource)
        } else {
            throw RuntimeException("Adapter is not subtype of BasicAdapter")
        }
    }
}