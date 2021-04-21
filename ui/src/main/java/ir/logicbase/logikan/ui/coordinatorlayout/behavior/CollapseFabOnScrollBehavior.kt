package ir.logicbase.logikan.ui.coordinatorlayout.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
import androidx.core.view.ViewCompat
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import ir.logicbase.logikan.ui.util.extension.dpToPixel

/**
 * The [Behavior] for a View within a [CoordinatorLayout] to hide the view off the
 * bottom of the screen when scrolling down, and show it when scrolling up.
 */
open class CollapseFabOnScrollBehavior<V : ExtendedFloatingActionButton>(
    context: Context?, attrs: AttributeSet?
) : CoordinatorLayout.Behavior<V>(context, attrs) {

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout, child: V, directTargetChild: View, target: View, axes: Int
    ) = axes == ViewCompat.SCROLL_AXIS_VERTICAL

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout, child: V, directTargetChild: View, target: View, axes: Int, type: Int
    ) = axes == ViewCompat.SCROLL_AXIS_VERTICAL

    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: V, target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int
    ) {
        if (dyConsumed > 0) {
            child.shrink(shrunkStateCallback)
        } else if (dyConsumed < 0) {
            child.extend()
        }
    }

    private val shrunkStateCallback = object : ExtendedFloatingActionButton.OnChangedCallback() {
        override fun onShrunken(extendedFab: ExtendedFloatingActionButton) {
            extendedFab.layoutParams.apply {
                height = extendedFab.context.dpToPixel(SHRUNK_LAYOUT_SIZE)
                width = extendedFab.context.dpToPixel(SHRUNK_LAYOUT_SIZE)
            }
        }
    }

    companion object {
        const val SHRUNK_LAYOUT_SIZE = 56
    }
}
