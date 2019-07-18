package ir.logicfan.core.ui.util.extension

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment

fun Fragment.addChildFragment(@IdRes viewId: Int, fragment: Fragment) {
    childFragmentManager.beginTransaction()
        .add(viewId, fragment)
        .commit()
}