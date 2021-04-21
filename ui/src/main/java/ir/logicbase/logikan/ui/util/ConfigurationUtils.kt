package ir.logicbase.logikan.ui.util

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import ir.logicbase.logikan.data.util.LocaleUtils
import java.util.*

object ConfigurationUtils {

    fun createConfigContext(context: Context): Context {
        val resources = context.resources
        val newConfig = Configuration(resources.configuration)
        updateConfiguration(newConfig)
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT < 25) {
            resources.updateConfiguration(newConfig, resources.displayMetrics)
        }
        return context.createConfigurationContext(newConfig)
    }

    fun updateConfiguration(newConfig: Configuration, locale: Locale = LocaleUtils.appLocale): Configuration {
        newConfig.setLocale(locale)
        return newConfig
    }
}