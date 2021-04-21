package ir.logicbase.logikan.data.util

import android.content.Context
import android.os.Build
import java.util.*

object LocaleUtils {
    private const val DEFAULT_LOCALE_CODE = "fa"

    var appLocale: Locale = Locale(DEFAULT_LOCALE_CODE).apply {
        Locale.setDefault(this)
    }
    val appLocaleCode: String
        get() = appLocale.country

    @JvmStatic
    fun getLocale(context: Context): Locale {
        val config = context.resources.configuration
        return if (Build.VERSION.SDK_INT >= 24) {
            config.locales.get(0)
        } else @Suppress("DEPRECATION") {
            config.locale
        }
    }
}