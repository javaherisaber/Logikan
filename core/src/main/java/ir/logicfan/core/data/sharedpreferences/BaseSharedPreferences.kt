package ir.logicfan.core.data.sharedpreferences


import android.annotation.SuppressLint
import android.content.Context
import android.preference.PreferenceManager
import android.provider.Settings
import ir.logicfan.core.R
import ir.logicfan.core.ui.util.LocaleUtils

/**
 * Basic preferences needed by every project
 * you need to extend this class to add more preferences to your app
 *
 * @param cipherSecret a secret code to encrypt shared preferences from manipulating on root devices
 */
abstract class BaseSharedPreferences (private val context: Context, cipherSecret: CharArray) :
    SecureSharedPreferences(cipherSecret, context, context.getSharedPreferences(PREF_ID, Context.MODE_PRIVATE)) {

    companion object {
        private const val PREF_ID = "securePreference"
        private const val KEY_DEVICE_ID = "deviceID"
    }

    /**
     * AndroidID of device
     * remain's unique throughout device restarts
     * it will only be revoked on phone factory reset (or it differ's on multi-user feature in android 5+)
     */
    val deviceID: String
        @SuppressLint("HardwareIds")
        get() {
            var storedDeviceID = getString(KEY_DEVICE_ID, null)
            if (storedDeviceID == null) {
                val androidDeviceID = Settings.Secure.getString(
                    context.contentResolver, Settings.Secure.ANDROID_ID
                )
                edit().putString(KEY_DEVICE_ID, androidDeviceID).apply()
                storedDeviceID = androidDeviceID
            }
            return storedDeviceID!!
        }

    /**
     * app language (default is fa)
     * this preference must be used for default share preference
     * because PreferenceActivity doesn't understand encrypted preference
     */
    var localeSetting: String
        get() {
            return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(context.getString(R.string.core_sharedPreferencesKey_localeSetting), LocaleUtils.DEFAULT_LOCALE)!!
        }
        set(value) {
            PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(context.getString(R.string.core_sharedPreferencesKey_localeSetting), value)
                .apply()
        }
}
