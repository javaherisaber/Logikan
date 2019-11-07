package ir.logicfan.core.ui.util.extension

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import ir.logicfan.core.R

fun Fragment.initializeToolbar(toolbar: Toolbar, displayTitle: Boolean = false, displayBack: Boolean = true) =
    (this.activity as AppCompatActivity).apply {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(displayTitle)
        supportActionBar?.setDisplayHomeAsUpEnabled(displayBack)
    }

fun Fragment.hideKeyBoard() {
    (this.activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
        view?.windowToken,
        0
    )
}

fun Fragment.setToolbarTitle(title: String) = (this.activity as AppCompatActivity).supportActionBar?.apply {
    setDisplayShowTitleEnabled(true)
    this.title = title
}

fun Fragment.openChildFragment(@IdRes viewId: Int, fragment: Fragment, addToBackStack: Boolean = false) {
    childFragmentManager.beginTransaction().apply {
        replace(viewId, fragment)
        if (addToBackStack) {
            addToBackStack(fragment.javaClass.simpleName)
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