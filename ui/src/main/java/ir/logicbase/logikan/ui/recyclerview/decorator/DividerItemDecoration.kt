package ir.logicbase.logikan.ui.recyclerview.decorator

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToInt
import androidx.recyclerview.widget.LinearLayoutManager

/**
 * DividerItemDecoration is a [RecyclerView.ItemDecoration] that can be used as a divider
 * between items of a [LinearLayoutManager]. It supports both [.HORIZONTAL] and
 * [.VERTICAL] orientations.
 *
 * @param context Current context, it will be used to access resources.
 * @param orientation Divider orientation. Should be [.HORIZONTAL] or [.VERTICAL].
 *
 * ```
 * dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
 * layoutManager.getOrientation());
 * recyclerView.addItemDecoration(dividerItemDecoration);
 * ```
 */
class DividerItemDecoration(
    context: Context,
    orientation: Int,
    private val padding: Int = 0,
    private val removeLastItemDecoration: Boolean = false
) : RecyclerView.ItemDecoration() {
    /**
     * @return the [Drawable] for this divider.
     */
    var drawable: Drawable?
    private val mBounds = Rect()

    /**
     * Current orientation. Either [.HORIZONTAL] or [.VERTICAL].
     *
     * Sets the orientation for this divider. This should be called if [RecyclerView.LayoutManager] changes orientation.
     */
    @Suppress("MemberVisibilityCanBePrivate")
    var orientation = orientation
        set(value) {
            require(value == HORIZONTAL || value == VERTICAL) { "Invalid orientation. It should be either HORIZONTAL or VERTICAL" }
            field = value
        }

    /**
     * Creates a divider RecyclerView.ItemDecoration that can be used with a LinearLayoutManager.
     */
    init {
        val a = context.obtainStyledAttributes(ATTRS)
        drawable = a.getDrawable(0)
        if (drawable == null) {
            Log.w(
                TAG, "@android:attr/listDivider was not set in the theme used for this "
                        + "DividerItemDecoration. Please set that attribute and call setDrawable()"
            )
        }
        a.recycle()
    }

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (drawable == null || parent.layoutManager == null) {
            return
        }
        if (orientation == VERTICAL) {
            drawVertical(drawable!!, canvas, parent)
        } else {
            drawHorizontal(drawable!!, canvas, parent)
        }
    }

    private fun drawVertical(drawable: Drawable, canvas: Canvas, parent: RecyclerView) {
        canvas.save()
        val left: Int
        val right: Int
        if (parent.clipToPadding && padding == 0) {
            left = parent.paddingLeft
            right = parent.width - parent.paddingRight
            canvas.clipRect(left, parent.paddingTop, right, parent.height - parent.paddingBottom)
        } else if (padding != 0) {
            left = padding
            right = parent.width - padding
            canvas.clipRect(left, parent.paddingTop, right, parent.height - parent.paddingBottom)
        } else {
            left = 0
            right = parent.width
        }
        for (index in 0 until parent.itemDecorationSize) {
            val child = parent.getChildAt(index)
            parent.getDecoratedBoundsWithMargins(child, mBounds)
            val bottom = mBounds.bottom + child.translationY.roundToInt()
            val top = bottom - drawable.intrinsicHeight
            drawable.setBounds(left, top, right, bottom)
            drawable.draw(canvas)
        }
        canvas.restore()
    }

    private fun drawHorizontal(drawable: Drawable, canvas: Canvas, parent: RecyclerView) {
        canvas.save()
        val top: Int
        val bottom: Int
        if (parent.clipToPadding && padding == 0) {
            top = parent.paddingTop
            bottom = parent.height - parent.paddingBottom
            canvas.clipRect(parent.paddingLeft, top, parent.width - parent.paddingRight, bottom)
        } else if (padding != 0) {
            top = padding
            bottom = parent.height - padding
            canvas.clipRect(parent.paddingLeft, top, parent.width - parent.paddingRight, bottom)
        } else {
            top = 0
            bottom = parent.height
        }
        for (index in 0 until parent.itemDecorationSize) {
            val child = parent.getChildAt(index)
            parent.layoutManager?.getDecoratedBoundsWithMargins(child, mBounds)
            val right = mBounds.right + child.translationX.roundToInt()
            val left = right - drawable.intrinsicWidth
            drawable.setBounds(left, top, right, bottom)
            drawable.draw(canvas)
        }
        canvas.restore()
    }

    private val RecyclerView.itemDecorationSize: Int
        get() = if (removeLastItemDecoration) this.childCount - 1 else this.childCount

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        if (drawable == null) {
            outRect.set(0, 0, 0, 0)
            return
        }
        val position = parent.getChildAdapterPosition(view)
        if (removeLastItemDecoration && (position == parent.childCount - 1)) {
            outRect.setEmpty()
            return
        }
        if (orientation == VERTICAL) {
            outRect.set(0, 0, 0, drawable!!.intrinsicHeight)
        } else {
            outRect.set(0, 0, drawable!!.intrinsicWidth, 0)
        }
    }

    companion object {
        const val HORIZONTAL = LinearLayout.HORIZONTAL
        const val VERTICAL = LinearLayout.VERTICAL
        private const val TAG = "DividerItemDecoration"
        private val ATTRS = intArrayOf(android.R.attr.listDivider)
    }
}
