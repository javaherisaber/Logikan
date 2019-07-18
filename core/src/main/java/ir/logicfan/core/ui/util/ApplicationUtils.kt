package ir.logicfan.core.ui.util

import android.content.Context
import android.content.pm.PackageInfo
import androidx.core.content.pm.PackageInfoCompat

/**
 * Utilities to work with application related attributes
 */
object ApplicationUtils {

    @JvmStatic
    fun getApkVersionCode(context: Context): String {
        val packageInfo = getPackageInfo(context)
        return PackageInfoCompat.getLongVersionCode(packageInfo).toString()
    }

    @JvmStatic
    fun getApkVersionName(context: Context): String = getPackageInfo(context).versionName

    @JvmStatic
    fun getPackageName(context: Context): String = getPackageInfo(context).packageName

    private fun getPackageInfo(context: Context): PackageInfo =
        context.packageManager.getPackageInfo(context.packageName, 0)
}