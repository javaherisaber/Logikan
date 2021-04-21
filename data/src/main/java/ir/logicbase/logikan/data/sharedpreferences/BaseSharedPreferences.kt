package ir.logicbase.logikan.data.sharedpreferences

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import androidx.preference.PreferenceManager
import ir.logicbase.logikan.data.util.LocaleUtils

/**
 * Basic preferences needed by every project
 * you need to extend this class to add more preferences to your app
 *
 * @param cipherSecret a secret code to encrypt shared preferences from manipulating on root devices
 */
abstract class BaseSharedPreferences(private val context: Context, cipherSecret: CharArray) :
    SecureSharedPreferences(cipherSecret, context, context.getSharedPreferences(PREF_ID, Context.MODE_PRIVATE)) {

    /**
     * AndroidID of device
     * remain's unique throughout device restarts
     * it will only be revoked on phone factory reset (or it differ's on multi-user feature in android 5+)
     */
    val deviceID: String
        @SuppressLint("HardwareIds")
        get() {
            var storedDeviceID = getString(KEY_DEVICE_ID, STRING_DEFAULT)
            if (storedDeviceID == STRING_DEFAULT) {
                val androidDeviceID = Settings.Secure.getString(
                    context.contentResolver, Settings.Secure.ANDROID_ID
                )
                edit().putString(KEY_DEVICE_ID, androidDeviceID).apply()
                storedDeviceID = androidDeviceID
            }
            return storedDeviceID
        }

    /**
     * app language (default is fa)
     * this preference must be used for default share preference
     * because PreferenceActivity doesn't understand encrypted preference
     */
    var appLocaleCode: String
        get() {
            return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(KEY_APP_LOCALE, LocaleUtils.appLocaleCode) ?: LocaleUtils.getLocale(context).country
        }
        set(value) {
            PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(KEY_APP_LOCALE, value)
                .apply()
        }

    companion object {
        private const val PREF_ID = "securePreference"
        private const val KEY_DEVICE_ID = "deviceID"
        private const val KEY_APP_LOCALE = "appLocale"

        const val STRING_DEFAULT = "null"
        const val INT_DEFAULT = -1
        const val FLOAT_DEFAULT = -1f
        const val LONG_DEFAULT = -1L
        const val BOOLEAN_DEFAULT = false
    }
}
