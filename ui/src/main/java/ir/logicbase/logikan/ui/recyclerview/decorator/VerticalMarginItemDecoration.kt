package ir.logicbase.logikan.ui.recyclerview.decorator

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Add margin to Vertical RecyclerView items
 */
class VerticalMarginItemDecoration(private val margin: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) = with(outRect) {
        // first item
        if (parent.getChildAdapterPosition(view) == 0) {
            top = margin
        }
        left =  margin
        right = margin
        bottom = margin
    }
}