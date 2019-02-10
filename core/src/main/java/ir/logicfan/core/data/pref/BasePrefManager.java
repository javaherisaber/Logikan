package ir.logicfan.core.data.pref;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.Settings;
import ir.logicfan.core.R;
import ir.logicfan.core.util.LocaleManager;

import javax.inject.Inject;

public class BasePrefManager extends SecureSharedPreferences {

    private static final String PREF_ID = "pref";
    private static final String KEY_DEVICE_ID = "deviceID";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";

    private Context context;

    @Inject
    public BasePrefManager(Context context) {
        super(context, context.getSharedPreferences(PREF_ID, Context.MODE_PRIVATE));
        this.context = context;
    }

    private void putDeviceID(String deviceId) {
        edit().putString(KEY_DEVICE_ID, deviceId).apply();
    }

    @SuppressLint("HardwareIds")
    public String getDeviceID() {
        String deviceID = getString(KEY_DEVICE_ID, null);
        if (deviceID == null) {
            deviceID = Settings.Secure.getString(
                    context.getContentResolver(), Settings.Secure.ANDROID_ID);
            putDeviceID(deviceID);
            return deviceID;
        } else {
            return deviceID;
        }
    }

    public boolean getIsLoggedIn() {
        return getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public void putIsLoggedInt(boolean value) {
        edit().putBoolean(KEY_IS_LOGGED_IN, value).apply();
    }

    public String getSettingsPrefLangList() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        return pref.getString(context.getString(R.string.key_pref_lang_list), LocaleManager.DEFAULT_LANG_VALUE);
    }

    public void putSettingsPrefLangList(String value) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(context.getString(R.string.key_pref_lang_list), value);
        editor.apply();
    }
}
