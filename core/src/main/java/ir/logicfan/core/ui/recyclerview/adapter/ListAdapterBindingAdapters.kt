package ir.logicfan.core.ui.recyclerview.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ir.logicfan.core.R
import ir.logicfan.core.databinding.CoreItemStateEmptyDummyBinding


/**
 * Define set of data biding adapters for ListAdapter class
 */
/**
 * Add new list of data for inside a given recyclerView
 */
@BindingAdapter(value = ["hasEmptyState", "submitList", "emptyText", "emptyDrawable", "emptyViewGroup"], requireAll = false)
fun RecyclerView.submitList(
    hasEmptyState: Boolean? = false,
    newList: List<Nothing>?,
    text: String?,
    drawable: Drawable?,
    viewGroup: ViewGroup?
) = newList?.let {
    val adapter = this.adapter ?: throw RuntimeException("Adapter not set when binding")
    if (adapter is ListAdapter<*, *>) {
        if (newList.isNullOrEmpty() && hasEmptyState == true) {
            val parent = viewGroup ?: this.parent as ViewGroup
            val inflater = LayoutInflater.from(parent.context)
            val emptyView = DataBindingUtil.inflate<CoreItemStateEmptyDummyBinding>(
                inflater,
                R.layout.core_item_state_empty_dummy, parent,
                false
            )
            drawable?.let {
                emptyView.imageViewCoreItemStateEmptyIcon.setImageDrawable(drawable)
            }
            text?.let {
                emptyView.textViewCoreItemStateEmptyMessage.text = text
            }
            parent.addView(emptyView.root)
        }
        adapter.submitList(newList)
    } else {
        throw RuntimeException("Adapter is not subtype of ListAdapter")
    }
}