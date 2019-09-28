package ir.logicfan.core.util

import android.content.Context
import android.net.Uri
import android.webkit.MimeTypeMap

fun getMimeTypeFromURL(url: String): String? {
    var type: String? = null
    val extension = MimeTypeMap.getFileExtensionFromUrl(url)
    if (extension != null) {
        type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
    }
    return type
}

fun getMimeTypeFromURI(uri: Uri, context: Context): String? {
    val contentResolver = context.contentResolver
    return contentResolver.getType(uri)
}

fun getFileTypeFromURI(uri: Uri, context: Context): String? {
    val contentResolver = context.contentResolver
    val mimeTypeMap = MimeTypeMap.getSingleton()
    return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri))
}