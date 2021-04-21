package ir.logicbase.logikan.ui.util

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
