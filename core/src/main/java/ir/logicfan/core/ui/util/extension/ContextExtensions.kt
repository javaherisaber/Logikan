package ir.logicfan.core.ui.util.extension

import android.content.Context
import android.content.pm.ApplicationInfo

/**
 * Check if application is debuggable or not
 */
fun Context.isDebuggable(): Boolean = 0 != applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE