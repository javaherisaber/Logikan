package ir.logicfan.core.util

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import java.util.*

object LocaleUtils {

    const val DEFAULT_LOCALE = "fa"

    @JvmStatic
    fun setLocale(context: Context, lang: String): Context {
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)
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