package ir.logicfan.core.ui.util

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import java.util.*

object LocaleUtils {

    internal const val DEFAULT_LOCALE = "fa"

    @JvmStatic
    @Deprecated("Use ConfigurationUtils.createConfigContext() instead " +
            "and set applicationLanguage first time app starts", level = DeprecationLevel.ERROR)
    fun setLocale(context: Context, lang: String): Context {
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val res = context.resources
        val config = Configuration(res.configuration)
        config.setLocale(locale)
        @Suppress("DEPRECATION")
        res.updateConfiguration(config, res.displayMetrics)
        return context.createConfigurationContext(config)
    }

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