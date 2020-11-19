package ir.logicbase.core.ui.util.extension

import android.app.Activity
import android.widget.Toast
import ir.logicbase.core.R

fun Activity.showTryAgainErrorToast() {
    Toast.makeText(this, R.string.core_error_try_again, Toast.LENGTH_LONG).show()
}