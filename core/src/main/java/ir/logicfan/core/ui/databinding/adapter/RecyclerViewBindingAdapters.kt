package ir.logicfan.core.ui.databinding.adapter

import android.graphics.drawable.Drawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.*
import ir.logicfan.core.R
import ir.logicfan.core.databinding.CoreItemStateEmptyBinding
import ir.logicfan.core.ui.recyclerview.decorator.GridMarginItemDecoration
import ir.logicfan.core.ui.recyclerview.decorator.HorizontalMarginItemDecoration
import ir.logicfan.core.ui.recyclerview.decorator.VerticalMarginItemDecoration

/**
 * Add a default gray color divider to your recyclerView
 *
 * @param hasItemDecoration if true itemDecoration will be added
 * @param dividerOrientation if provided it decide orientation, if null Vertical will be used
 */
@BindingAdapter(
    value = ["hasDefaultDividerItemDecoration", "dividerOrientation"],
    requireAll = false
)
fun RecyclerView.addDefaultDividerItemDecoration(
    hasItemDecoration: Boolean,
    dividerOrientation: Int?
) {
    if (hasItemDecoration) {
        if (dividerOrientation == null) {
            // no orientation provided, use VERTICAL by default
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        } else {
            // orientation provided
            addItemDecoration(DividerItemDecoration(context, dividerOrientation))
        }
    }
}

/**
 * Add margin to recyclerView items
 *
 * @param hasItemDecoration if true decorator will be added
 * @param margin applies to all sides of your item
 */
@BindingAdapter(
    value = ["hasMarginItemDecoration", "itemDecorationMargin"],
    requireAll = false
)
fun RecyclerView.addGridMarginItemDecorator(
    hasItemDecoration: Boolean,
    margin: Int?
) {
    if (hasItemDecoration) {
        var itemMargin = resources.getDimension(R.dimen.core_margin_low).toInt()
        margin?.let {
            itemMargin = it
        }
        when (val itemLayoutManager = layoutManager) {
            is GridLayoutManager -> addItemDecoration(GridMarginItemDecoration(itemMargin))
            is LinearLayoutManager -> when (itemLayoutManager.orientation) {
                LinearLayoutManager.HORIZONTAL -> addItemDecoration(
                    HorizontalMarginItemDecoration(itemMargin)
                )
                LinearLayoutManager.VERTICAL -> addItemDecoration(
                    VerticalMarginItemDecoration(itemMargin)
                )
            }
        }
    }
}

/**
 * Set nestedScrollingViewEnabled attribute and respect backward compatibility
 */
@BindingAdapter("isNestedScrollingEnabled")
fun RecyclerView.setNestedScrollingViewEnabled(isEnabled: Boolean) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        this.isNestedScrollingEnabled = isEnabled
    } else {
        ViewCompat.setNestedScrollingEnabled(this, isEnabled)
    }
}

/**
 * Add new list of data for inside a given recyclerView
 * @param isZeroIndex shall we add empty view to parent's index 0
 */
@BindingAdapter(
    value = [
        "hasEmptyState", "submitList", "emptyText", "emptyDrawable", "emptyViewGroup", "isZeroIndex"
    ], requireAll = false
)
fun RecyclerView.submitList(
    hasEmptyState: Boolean?,
    newList: List<Nothing>?,
    emptyText: String?,
    emptyDrawable: Drawable?,
    viewGroup: ViewGroup?,
    isZeroIndex: Boolean?
) = newList?.let {
    val adapter = this.adapter ?: throw RuntimeException("Adapter not set when binding")
    if (adapter is ListAdapter<*, *>) {
        adapter.submitList(newList)
        val parent = viewGroup ?: this.parent as ViewGroup
        if (newList.isEmpty() && hasEmptyState == true) {
            if (parent.findViewById<View?>(R.id.constraintLayout_coreItemStateEmpty) != null) {
                return@let
            }
            val inflater = LayoutInflater.from(parent.context)
            val emptyView = DataBindingUtil.inflate<CoreItemStateEmptyBinding>(
                inflater, R.layout.core_item_state_empty, parent, false
            )
            emptyDrawable?.let { emptyView.imageViewCoreItemStateEmptyIcon.setImageDrawable(emptyDrawable) }
            emptyText?.let { emptyView.textViewCoreItemStateEmptyMessage.text = emptyText }
            if (isZeroIndex != null && isZeroIndex) {
                parent.addView(emptyView.root, 0)
            } else {
                parent.addView(emptyView.root)
            }
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