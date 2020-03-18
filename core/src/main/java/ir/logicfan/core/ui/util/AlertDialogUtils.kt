/*
 * Copyright (C) Logicfan, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential
 * Written by Mahdi Javaheri Saber <m.javaherisaber@gmail.com>, Mohammad Hosein Abedini <mohammadhoseinabedini@gmail.com>  2020.
 */

package ir.logicfan.core.ui.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.annotation.NonNull
import androidx.appcompat.app.AlertDialog

object AlertDialogUtils {

    @JvmStatic
    fun showDialogWithPermissionScreenButton(
        @NonNull context: Context,
        title: String,
        message: String,
        positiveButtonText: String
    ) {
        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(positiveButtonText) { _, _ ->
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                intent.data = Uri.parse("package:" + context.packageName)
                context.startActivity(intent)
            }.show()
    }

    @JvmStatic
    fun showDialogWithLocationScreenButton(
        @NonNull context: Context,
        title: String,
        message: String,
        positiveButtonText: String
    ) {
        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(positiveButtonText) { _, _ ->
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                context.startActivity(intent)
            }.show()
    }
}
