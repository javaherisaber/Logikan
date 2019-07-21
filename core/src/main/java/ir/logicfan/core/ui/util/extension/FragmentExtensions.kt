package ir.logicfan.core.ui.util.extension

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment

fun Fragment.openChildFragment(@IdRes viewId: Int, fragment: Fragment) {
    childFragmentManager.beginTransaction()
        .replace(viewId, fragment)
        .commit()
}