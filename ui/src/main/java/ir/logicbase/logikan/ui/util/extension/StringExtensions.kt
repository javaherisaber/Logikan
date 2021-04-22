@file:JvmName("StringExtensions")

package ir.logicbase.logikan.ui.util.extension

import android.os.Build
import android.text.Html
import android.text.Spanned
import android.webkit.MimeTypeMap

fun String?.stripHtml(): Spanned = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
    Html.fromHtml(this ?: "", Html.FROM_HTML_MODE_COMPACT)
} else @Suppress("DEPRECATION") {
    Html.fromHtml(this ?: "")
}

/**
 * Get mime type for a given url
 */
fun String.mimeTypeForUrl(): String? {
    var type: String? = null
    val extension = MimeTypeMap.getFileExtensionFromUrl(this)
    if (extension != null) {
        type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
    }
    return type
}