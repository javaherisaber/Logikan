package ir.logicfan.core.ui.util.extension

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.annotation.IdRes
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.ui.NavigationUI.onNavDestinationSelected
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.navigation.NavigationView
import java.lang.ref.WeakReference

/**
 * handle navigation of menu items which have same id defined in your navigation graph
 * example: when you have a menu item which navigates to a specific fragment
 * you don't need to handle that yourself, this function handle it for you
 *
 * @return true if menu handled, false otherwise
 */
fun NavigationView.handleMenuItemWithNavController(menuItem: MenuItem, navController: NavController): Boolean {
    val handled = onNavDestinationSelected(menuItem, navController)
    if (handled) {
        val parent = this.parent
        if (parent is DrawerLayout) {
            parent.closeDrawer(this)
        } else {
            val bottomSheetBehavior = findBottomSheetBehavior(this)
            if (bottomSheetBehavior != null) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
            }
        }
    }
    val weakReference = WeakReference(this)
    navController.addOnDestinationChangedListener(
        object : NavController.OnDestinationChangedListener {
            override fun onDestinationChanged(
                controller: NavController,
                destination: NavDestination, arguments: Bundle?
            ) {
                val view = weakReference.get()
                if (view == null) {
                    navController.removeOnDestinationChangedListener(this)
                    return
                }
                val menu = view.menu
                var h = 0
                val size = menu.size()
                while (h < size) {
                    val item = menu.getItem(h)
                    item.isChecked = matchDestination(destination, item.itemId)
                    h++
                }
            }
        })
    return handled
}

/**
 * Determines whether the given `destId` matches the NavDestination. This handles
 * both the default case (the destination's id matches the given id) and the nested case where
 * the given id is a parent/grandparent/etc of the destination.
 */
internal fun matchDestination(destination: NavDestination, @IdRes destId: Int): Boolean {
    var currentDestination: NavDestination? = destination
    while (currentDestination!!.id != destId && currentDestination.parent != null) {
        currentDestination = currentDestination.parent
    }
    return currentDestination.id == destId
}

/**
 * Walks up the view hierarchy, trying to determine if the given View is contained within
 * a bottom sheet.
 */
internal fun findBottomSheetBehavior(view: View): BottomSheetBehavior<*>? {
    val params = view.layoutParams
    if (params !is CoordinatorLayout.LayoutParams) {
        val parent = view.parent
        return if (parent is View) {
            findBottomSheetBehavior(parent as View)
        } else null
    }
    val behavior = params
        .behavior
    return if (behavior !is BottomSheetBehavior<*>) {
        // We hit a CoordinatorLayout, but the View doesn't have the BottomSheetBehavior
        null
    } else behavior
}