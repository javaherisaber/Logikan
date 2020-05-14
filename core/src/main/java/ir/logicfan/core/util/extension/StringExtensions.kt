package ir.logicfan.core.util.extension

import android.os.Build
import android.text.Html
import android.text.Spanned

fun String?.stripHtml(): Spanned =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this ?: "", Html.FROM_HTML_MODE_COMPACT)
    } else @Suppress("DEPRECATION") {
        Html.fromHtml(this ?: "")
    }

fun String.isValidIranMobile(): Boolean = this.matches("(0(9[0-9]{9}))|(\\+98(9[0-9]{9}))".toRegex())

/**
 * Add Bearer prefix to token string
 */
fun String.toBearerToken(): String = "Bearer $this"

/**
 * Remove ',' chars from text
 */
fun String.stripThousandsSeparator(): String = this.replace(",", "")