package ir.logicfan.core.ui.databinding.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ir.logicfan.core.R
import ir.logicfan.core.databinding.CoreItemStateEmptyBinding

/**
 * Add new list of data for inside a given recyclerView
 */
@BindingAdapter(value = ["hasEmptyState", "submitList", "emptyText", "emptyDrawable", "emptyViewGroup"], requireAll = false)
fun RecyclerView.submitList(
    hasEmptyState: Boolean?, newList: List<Nothing>?, emptyText: String?, emptyDrawable: Drawable?, viewGroup: ViewGroup?
) = newList?.let {
    val adapter = this.adapter ?: throw RuntimeException("Adapter not set when binding")
    if (adapter is ListAdapter<*, *>) {
        adapter.submitList(newList)
        val parent = viewGroup ?: this.parent as ViewGroup
        if (newList.isEmpty() && hasEmptyState == true) {
            val inflater = LayoutInflater.from(parent.context)
            val emptyView = DataBindingUtil.inflate<CoreItemStateEmptyBinding>(
                inflater, R.layout.core_item_state_empty, parent, false
            )
            emptyDrawable?.let { emptyView.imageViewCoreItemStateEmptyIcon.setImageDrawable(emptyDrawable) }
            emptyText?.let { emptyView.textViewCoreItemStateEmptyMessage.text = emptyText }
            parent.addView(emptyView.root)
        } else {
            parent.findViewById<View>(R.id.constraintLayout_coreItemStateEmpty)?.apply {
                // if emptyView already added, and now list is not empty
                parent.removeView(this)
            }
        }
    } else {
        throw RuntimeException("Adapter is not subtype of ListAdapter")
    }
}