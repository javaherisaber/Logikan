package ir.logicbase.core.ui.util

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import java.util.*

object ConfigurationUtils {

    var applicationLanguage: String = LocaleUtils.DEFAULT_LOCALE

    fun createConfigContext(context: Context): Context {
        val resources = context.resources
        val newConfig = Configuration(resources.configuration)
        updateConfiguration(newConfig)
        if (Build.VERSION.SDK_INT < 25) {
            @Suppress("DEPRECATION")
            resources.updateConfiguration(newConfig, resources.displayMetrics)
        }
        return context.createConfigurationContext(newConfig)
    }

    fun updateConfiguration(newConfig: Configuration): Configuration {
        val locale = Locale(applicationLanguage).apply {
            Locale.setDefault(this)
        }
        newConfig.setLocale(locale)
        return newConfig
    }
}