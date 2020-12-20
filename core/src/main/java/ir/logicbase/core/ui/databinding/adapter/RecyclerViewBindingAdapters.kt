package ir.logicbase.core.ui.databinding.adapter

import android.graphics.drawable.Drawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ir.logicbase.core.R
import ir.logicbase.core.databinding.CoreItemStateEmptyBinding
import ir.logicbase.core.ui.recyclerview.decorator.DividerItemDecoration
import ir.logicbase.core.ui.recyclerview.decorator.GridMarginItemDecoration
import ir.logicbase.core.ui.recyclerview.decorator.HorizontalMarginItemDecoration
import ir.logicbase.core.ui.recyclerview.decorator.VerticalMarginItemDecoration

/**
 * Add a default gray color divider to your recyclerView
 *
 * @param hasItemDecoration if true itemDecoration will be added
 * @param dividerOrientation if provided it decide orientation, if null Vertical will be used
 */
@BindingAdapter(
    value = ["hasDefaultDividerItemDecoration", "dividerOrientation", "itemDecorationPadding", "removeLastItemDecoration"],
    requireAll = false
)
fun RecyclerView.addDefaultDividerItemDecoration(
    hasItemDecoration: Boolean,
    dividerOrientation: Int?,
    itemDecorationPadding: Int?,
    removeLastItemDecoration: Boolean,
) {
    if (hasItemDecoration) {
        if (dividerOrientation == null) {
            // no orientation provided, use VERTICAL by default
            addItemDecoration(
                DividerItemDecoration(
                    context, DividerItemDecoration.VERTICAL, itemDecorationPadding ?: 0, removeLastItemDecoration
                )
            )
        } else {
            // orientation provided
            addItemDecoration(
                DividerItemDecoration(
                    context, dividerOrientation, itemDecorationPadding ?: 0, removeLastItemDecoration
                )
            )
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
                RecyclerView.HORIZONTAL -> addItemDecoration(
                    HorizontalMarginItemDecoration(itemMargin)
                )
                RecyclerView.VERTICAL -> addItemDecoration(
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
        "hasEmptyState", "submitList", "emptyText", "emptyDrawable", "emptyImageUrl", "emptyViewGroup", "isZeroIndex"
    ], requireAll = false
)
fun RecyclerView.submitList(
    hasEmptyState: Boolean?,
    newList: List<Nothing>?,
    emptyText: String?,
    emptyDrawable: Drawable?,
    emptyImageUrl: String?,
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
            emptyImageUrl?.let {
                Glide.with(context).load(it).into(emptyView.imageViewCoreItemStateEmptyIcon)
            }
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