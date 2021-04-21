package ir.logicbase.logikan.ui.recyclerview.decorator

import android.graphics.Rect
import android.view.View
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView

/**
 * Add margin to Horizontal RecyclerView items
 */
class HorizontalMarginItemDecoration(private val margin: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) = with(outRect) {
        val isLayoutRtl = parent.layoutDirection == ViewCompat.LAYOUT_DIRECTION_RTL
        // first item
        if (parent.getChildAdapterPosition(view) == 0) {
            if (isLayoutRtl) {
                right = margin
            } else {
                left = margin
            }
        }
        if (isLayoutRtl) {
            left = margin
        } else {
            right = margin
        }
        top = margin
        bottom = margin
    }
}