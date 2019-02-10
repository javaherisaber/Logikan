package ir.logicfan.core.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import ir.logicfan.core.di.scope.PerActivity;

@PerActivity
public class AlertDialogUtils {

    public static void showDialogWithPermissionScreenButton(Context context, String title, String message, String positiveButtonText) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButtonText, (dialog, which) -> {
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    intent.setData(Uri.parse("package:" + context.getPackageName()));
                    context.startActivity(intent);
                }).show();
    }

    public static void showDialogWithNegativeAndPositiveButtons(Context context, String title, String message,
                                                                String positiveButtonText,
                                                                DialogInterface.OnClickListener positiveButtonClickListener,
                                                                String negativeButtonText,
                                                                DialogInterface.OnClickListener negativeButtonClickListener) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButtonText, positiveButtonClickListener)
                .setNegativeButton(negativeButtonText, negativeButtonClickListener)
                .show();
    }
}
