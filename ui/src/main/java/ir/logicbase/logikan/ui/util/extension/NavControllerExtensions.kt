package ir.logicbase.logikan.ui.util.extension

import android.net.Uri
import androidx.navigation.NavController
import ir.logicbase.logikan.utils.extension.parseDeepLink

/**
 * Navigate to deep link with list of path <key, value> being replaced in uri
 */
@Suppress("RegExpRedundantEscape")
fun NavController.navigateDeepLink(res: String, vararg params: Pair<String, Any?>) {
    val deepLink = res.parseDeepLink(params)
    navigate(Uri.parse(deepLink))
}