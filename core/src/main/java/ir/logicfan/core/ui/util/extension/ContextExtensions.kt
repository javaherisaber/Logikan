package ir.logicfan.core.ui.util.extension

import android.content.Context
import android.content.pm.ApplicationInfo
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

/**
 * Check if application is debuggable or not
 */
fun Context.isDebuggable(): Boolean = 0 != applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE

/**
 * Convenience method for [ContextCompat.getDrawable]
 */
fun Context.drawableAt(@DrawableRes drawable: Int): Drawable =
    ContextCompat.getDrawable(this, drawable) ?: error("Drawable with given resId not found")

/**
 * Convenience method for [ContextCompat.getColor]
 */
@ColorInt
fun Context.colorAt(@ColorRes color: Int): Int = ContextCompat.getColor(this, color)

/**
 * Get color from theme eg. context.themeColorAt(R.attr.colorPrimary)
 */
@ColorInt
fun Context.themeColorAt(@AttrRes colorAttr: Int): Int = TypedValue().run typedValue@{
    this@themeColorAt.theme.resolveAttribute(colorAttr, this@typedValue, true)
        .run { if (this) data else Color.BLACK }
}