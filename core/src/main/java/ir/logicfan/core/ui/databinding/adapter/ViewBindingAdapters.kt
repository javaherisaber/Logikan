package ir.logicfan.core.ui.databinding.adapter

import android.view.View
import androidx.databinding.BindingAdapter
import at.blogc.android.views.ExpandableTextView

object ViewBindingAdapters {

    /**
     * Toggle expandableTextView when clicked on expand/collapse button
     */
    @BindingAdapter("expandableTextViewAnchor")
    @JvmStatic
    fun expandableTextViewAnchor(view: View, expandableTextViewAnchor: ExpandableTextView?) {
        expandableTextViewAnchor?.let {
            view.setOnClickListener {
                expandableTextViewAnchor.toggle()
            }
        }
    }
}