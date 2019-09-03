package ir.logicfan.core.ui.recyclerview.decorator

import android.graphics.Rect
import android.view.View
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Add margin to Grid RecyclerView items
 */
class GridMarginItemDecoration(private val margin: Int) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            val spanCount = (parent.layoutManager as GridLayoutManager).spanCount
            val position = parent.getChildAdapterPosition(view)
            if (position in 0 until spanCount) {
                // only first row have top margin
                top = margin
            }
            val isLayoutRtl = parent.layoutDirection == ViewCompat.LAYOUT_DIRECTION_RTL
            if (position.rem(spanCount) == 0) {
                // first column
                if (isLayoutRtl) {
                    right = margin
                    left = margin / 2
                } else {
                    left = margin
                    right = margin / 2
                }
            }
            if (position.rem(spanCount) == (spanCount - 1)) {
                // last column
                if (isLayoutRtl) {
                    left = margin
                    right = margin / 2
                } else {
                    right = margin
                    left = margin / 2
                }
            }
            bottom = margin
        }
    }
}