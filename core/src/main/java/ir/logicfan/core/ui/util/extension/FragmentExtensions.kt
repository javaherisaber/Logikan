package ir.logicfan.core.ui.util.extension

import android.widget.Toast
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import ir.logicfan.core.R

fun Fragment.openChildFragment(@IdRes viewId: Int, fragment: Fragment, addToBackStack: Boolean = false) {
    childFragmentManager.beginTransaction().apply {
        replace(viewId, fragment)
        if (addToBackStack) {
            addToBackStack(fragment.tag)
        }
        commit()
    }
}

fun Fragment.notImplementedToast() {
    Toast.makeText(this.context, "Not implemented yet!", Toast.LENGTH_SHORT).show()
}

fun Fragment.showTryLaterErrorToast() {
    Toast.makeText(this.context, R.string.core_error_try_later, Toast.LENGTH_LONG).show()
}

fun Fragment.showTryAgainErrorToast() {
    Toast.makeText(this.context, R.string.core_error_try_again, Toast.LENGTH_LONG).show()
}