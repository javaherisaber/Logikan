package ir.logicbase.core.util.extension

import android.os.Build
import android.text.Html
import android.text.Spanned
import android.webkit.MimeTypeMap

fun String?.stripHtml(): Spanned = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
    Html.fromHtml(this ?: "", Html.FROM_HTML_MODE_COMPACT)
} else @Suppress("DEPRECATION") {
    Html.fromHtml(this ?: "")
}

fun String.isValidIranMobile(): Boolean = this.matches("(0(9[0-9]{9}))|(\\+98(9[0-9]{9}))".toRegex())

/**
 * Convert 09XXX to 989XXX
 */
fun String.toIranMobile(): String = this.replaceFirst("0", "98")

fun String.fromIranMobile(): String = this.replaceFirst("98", "0")

/**
 * Add Bearer prefix to token string
 */
fun String.toBearerToken(): String = if (this != "null") "Bearer $this" else this

/**
 * Remove ',' chars from text
 */
fun String.stripThousandsSeparator(): String = this.replace(",", "")

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

/**
 * Parse deep link with given params
 */
@Suppress("RegExpRedundantEscape")
fun String.parseDeepLink(params: Array<out Pair<String, Any?>>): String {
    var deepLink = this
    params.forEach { (key, value) ->
        if (value == null || (value is String && value == "null")) {
            // remove null values
            deepLink = deepLink.replace("""\?($key)=\{\1\}""".toRegex(), "")
            deepLink = deepLink.replace("""\?($key)=\{\1\}&""".toRegex(), "")
            deepLink = deepLink.replace("""&($key)=\{\1\}""".toRegex(), "")
        } else {
            deepLink = deepLink.replace("{$key}", value.toString())
        }
    }
    // remove null queries
    deepLink = deepLink.replace("""\?(.*)=\{\1\}""".toRegex(), "")
    deepLink = deepLink.replace("""\?(.*)=\{\1\}&""".toRegex(), "")
    deepLink = deepLink.replace("""&(.*)=\{\1\}""".toRegex(), "")
    return deepLink
}

/**
 * Decode unicode characters from receiver into utf-8 text
 */
fun String.decodeUnicode(): String {
    var aChar: Char
    val len = this.length
    val outBuffer = StringBuffer(len)
    var x = 0
    while (x < len) {
        aChar = this[x++]
        if (aChar == '\\') {
            aChar = this[x++]
            if (aChar == 'u') {
                var value = 0
                for (i in 0..3) {
                    aChar = this[x++]
                    value = when (aChar) {
                        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> (value shl 4) + aChar.toInt() - '0'.toInt()
                        'a', 'b', 'c', 'd', 'e', 'f' -> (value shl 4) + 10 + aChar.toInt() - 'a'.toInt()
                        'A', 'B', 'C', 'D', 'E', 'F' -> (value shl 4) + 10 + aChar.toInt() - 'A'.toInt()
                        else -> throw IllegalArgumentException("Malformed   \\uxxxx   encoding.")
                    }
                }
                outBuffer.append(value.toChar())
            } else {
                when (aChar) {
                    't' -> aChar = '\t'
                    'r' -> aChar = '\r'
                    'n' -> aChar = '\n'
                }
                outBuffer.append(aChar)
            }
        } else outBuffer.append(aChar)
    }
    return outBuffer.toString()
}