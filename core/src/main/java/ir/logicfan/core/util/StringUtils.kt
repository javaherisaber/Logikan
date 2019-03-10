package ir.logicfan.core.util

import android.os.Build
import android.text.Html
import android.text.Spanned

fun String.stripHtml(): Spanned {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT)
    } else @Suppress("DEPRECATION") {
        Html.fromHtml(this)
    }
}

fun String.isValidIranMobile(): Boolean {
    return this.matches("(0(9[0-9]{9}))|(\\+98(9[0-9]{9}))".toRegex())
}
