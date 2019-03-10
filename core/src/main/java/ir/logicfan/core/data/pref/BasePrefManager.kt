package ir.logicfan.core.data.pref


import android.annotation.SuppressLint
import android.content.Context
import android.preference.PreferenceManager
import android.provider.Settings
import ir.logicfan.core.R
import ir.logicfan.core.util.LocaleManager
import javax.inject.Inject

class BasePrefManager @Inject
constructor(private val context: Context) :
    SecureSharedPreferences(context, context.getSharedPreferences(PREF_ID, Context.MODE_PRIVATE)) {

    companion object {
        private const val PREF_ID = "pref"
        private const val KEY_DEVICE_ID = "deviceID"
        private const val KEY_IS_LOGGED_IN = "isLoggedIn"
    }

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

    var isLoggedIn: Boolean
        get() = getBoolean(KEY_IS_LOGGED_IN, false)
        set(value) = edit().putBoolean(KEY_IS_LOGGED_IN, value).apply()

    var settingsPrefLangList: String
        get() {
            return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(context.getString(R.string.key_pref_lang_list), LocaleManager.DEFAULT_LANG_VALUE)!!
        }
        set(value) {
            PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(context.getString(R.string.key_pref_lang_list), value)
                .apply()
        }
}
