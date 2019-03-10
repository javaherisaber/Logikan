package ir.logicfan.core.util

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.provider.Settings

object AlertDialogUtils {

    fun showDialogWithPermissionScreenButton(
        context: Context,
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

    @Deprecated("This method doesn't add any value",
        ReplaceWith("built-in android alert dialog builder or KTX extension"))
    fun showDialogWithNegativeAndPositiveButtons(
        context: Context, title: String, message: String,
        positiveButtonText: String,
        positiveButtonClickListener: DialogInterface.OnClickListener,
        negativeButtonText: String,
        negativeButtonClickListener: DialogInterface.OnClickListener
    ) {
        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(positiveButtonText, positiveButtonClickListener)
            .setNegativeButton(negativeButtonText, negativeButtonClickListener)
            .show()
    }
}
