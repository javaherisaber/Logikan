package ir.logicfan.core.ui.util.extension

import android.app.Activity
import android.widget.Toast
import ir.logicfan.core.R

fun Activity.showTryAgainErrorToast() {
    Toast.makeText(this, R.string.core_error_try_again, Toast.LENGTH_LONG).show()
}