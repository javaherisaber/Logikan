package ir.logicfan.core.ui.util.extension

import android.net.Uri
import androidx.navigation.NavController

/**
 * Navigate to deep link with list of path <key, value> being replaced in uri
 */
@Suppress("RegExpRedundantEscape")
fun NavController.navigateDeepLink(res: String, path: Array<out Pair<String, Any?>>) {
    var deepLink = res
    path.forEach { (key, value) ->
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
    navigate(Uri.parse(deepLink))
}