/*
 * Copyright (C) Logicfan, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential
 * Written by Mahdi Javaheri Saber <m.javaherisaber@gmail.com>, Mohammad Hosein Abedini <mohammadhoseinabedini@gmail.com>  2020.
 */

package ir.logicfan.core.ui.util

import android.content.Context
import android.os.Build
import android.provider.Settings
import javax.inject.Inject

/**
 * Utilities to work with device, hardware and android related attributes
 */
class DeviceUtils @Inject constructor(private val context: Context) {

    /**
     * Real name of your device (not coded names like j730f) eg. Galaxy J7 Pro
     */
    fun getDeviceName(): String = Settings.Global.getString(context.contentResolver, "device_name")

    /**
     * Descriptive name of your device = Samsung Galaxy J7 Pro, Android 9 (28)
     */
    fun getDescriptiveDeviceName(): String {
        val manufacturer = getDeviceManufacturer()
        val deviceName = getDeviceName()
        val androidVersion = getAndroidVersionCode()
        val androidApi = getAndroidAPICode()
        return "$manufacturer $deviceName, Android $androidVersion ($androidApi)"
    }

    companion object {
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
    }
}