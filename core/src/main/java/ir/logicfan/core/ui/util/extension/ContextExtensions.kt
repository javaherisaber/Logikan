package ir.logicfan.core.ui.util.extension

import android.content.Context
import android.content.pm.ApplicationInfo
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.TypedValue
import android.webkit.MimeTypeMap
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import ir.logicfan.core.util.extension.mimeTypeForUrl

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

fun Context.getBitmapFromURL(url: String, operation: (data: BitmapMimeData) -> Unit) {
    Glide.with(this)
        .asBitmap()
        .load(url)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                url.mimeTypeForUrl()?.let { mimeType -> operation(BitmapMimeData(resource, mimeType)) }
            }

            override fun onLoadCleared(placeholder: Drawable?) {}
        })
}

data class BitmapMimeData(val bitmap: Bitmap, val mimeType: String)

fun Context.getFileTypeFromUri(uri: Uri): String {
    return contentResolver.getType(uri)?.split("/")?.toTypedArray()?.get(0) ?: error("Wrong uri")
}

fun Context.getMimeTypeFromUri(uri: Uri) = contentResolver.getType(uri) ?: error("No mimeType found for given uri")

fun Context.getFileExtensionFromURI(uri: Uri): String? {
    val mimeTypeMap = MimeTypeMap.getSingleton()
    return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri))
}

fun Context.getBitmapFromUri(uri: Uri): Bitmap {
    if (getFileTypeFromUri(uri) == "image") {
        return BitmapFactory.decodeStream(contentResolver?.openInputStream(uri))
    } else {
        error("uri does not contain image")
    }
}

fun Context.getBitmapMimeFromUri(uri: Uri) = BitmapMimeData(getBitmapFromUri(uri), getMimeTypeFromUri(uri))