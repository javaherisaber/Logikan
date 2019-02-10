package ir.logicfan.core.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import javax.inject.Singleton;

/**
 * Check device's network connectivity
 */
@Singleton
public class Connectivity {

    public static boolean isUserOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return (netInfo != null && netInfo.isConnected());
    }
}