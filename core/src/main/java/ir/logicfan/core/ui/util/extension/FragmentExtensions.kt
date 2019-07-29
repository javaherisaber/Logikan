package ir.logicfan.core.ui.util.extension

import android.widget.Toast
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment

fun Fragment.openChildFragment(@IdRes viewId: Int, fragment: Fragment) {
    childFragmentManager.beginTransaction()
        .replace(viewId, fragment)
        .commit()
}

fun Fragment.notImplementedToast() {
    Toast.makeText(this.context, "Not implemented yet!", Toast.LENGTH_SHORT).show()
}