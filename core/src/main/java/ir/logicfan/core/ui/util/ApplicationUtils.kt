/*
 * Copyright (C) Logicfan, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential
 * Written by Mahdi Javaheri Saber <m.javaherisaber@gmail.com>, Mohammad Hosein Abedini <mohammadhoseinabedini@gmail.com>  2020.
 */

package ir.logicfan.core.ui.util

import android.content.Context
import android.content.pm.PackageInfo
import androidx.core.content.pm.PackageInfoCompat
import javax.inject.Inject

/**
 * Utilities to work with application related attributes
 */
class ApplicationUtils @Inject constructor(private val context: Context) {

    private val mPackageInfo: PackageInfo
        get() = context.packageManager.getPackageInfo(context.packageName, 0)

    fun getApkVersionCode(): String {
        return PackageInfoCompat.getLongVersionCode(mPackageInfo).toString()
    }

    fun getApkVersionName(): String = mPackageInfo.versionName

    fun getPackageName(): String = mPackageInfo.packageName
}