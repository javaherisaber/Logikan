package ir.logicfan.core.ui.util

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings

object AlertDialogUtils {

    @JvmStatic
    fun showDialogWithPermissionScreenButton(
        context: Context,
        title: String,
        message: String,
        positiveButtonText: String,
        negativeButtonText: String,
        neutralButtonText: String,
        onNeutralButtonClick: () -> Unit
    ) {
        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(positiveButtonText) { _, _ ->
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                intent.data = Uri.parse("package:" + context.packageName)
                context.startActivity(intent)
            }.setNegativeButton(negativeButtonText) { dialog, _ ->
                dialog.dismiss()
            }.setNeutralButton(neutralButtonText) { dialog, _ ->
                dialog.dismiss()
                onNeutralButtonClick()
            }.show()
    }
}
