package ir.logicfan.core.ui.util

import android.content.Context
import android.os.Build
import android.provider.Settings

/**
 * Utilities to work with device, hardware and android related attributes
 */
object DeviceUtils {

    /**
     * Real name of your device (not coded names like j730f) eg. Galaxy J7 Pro
     */
    @JvmStatic
    fun getDeviceName(context: Context): String = Settings.Global.getString(context.contentResolver, "device_name")

    @JvmStatic
    fun getDeviceManufacturer(): String = Build.MANUFACTURER

    @JvmStatic
    fun getAndroidAPICode(): Int = Build.VERSION.SDK_INT

    @JvmStatic
    fun getAndroidVersionCode(): String = Build.VERSION.RELEASE

    /**
     * eg. Pie, Oreo
     */
    @JvmStatic
    fun getAndroidVersionName(): String {
        val fields = Build.VERSION_CODES::class.java.fields
        var codeName = "UNKNOWN"
        fields.filter { it.getInt(Build.VERSION_CODES::class) == Build.VERSION.SDK_INT }
            .forEach { codeName = it.name }
        return codeName
    }

    /**
     * Descriptive name of your device = Samsung Galaxy J7 Pro, Android 9 (28)
     */
    @JvmStatic
    fun getDescriptiveDeviceName(context: Context): String {
        val manufacturer = getDeviceManufacturer()
        val deviceName = getDeviceName(context)
        val androidVersion = getAndroidVersionCode()
        val androidApi = getAndroidAPICode()
        return "$manufacturer $deviceName, Android $androidVersion ($androidApi)"
    }
}