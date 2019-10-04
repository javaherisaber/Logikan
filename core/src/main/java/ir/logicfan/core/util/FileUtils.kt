package ir.logicfan.core.util

import android.content.Context
import android.net.Uri
import android.webkit.MimeTypeMap

object FileUtils {

    @JvmStatic
    fun getMimeTypeFromURL(url: String): String? {
        var type: String? = null
        val extension = MimeTypeMap.getFileExtensionFromUrl(url)
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
        }
        return type
    }

    @JvmStatic
    fun getMimeTypeFromURI(uri: Uri, context: Context): String? {
        val contentResolver = context.contentResolver
        return contentResolver.getType(uri)
    }

    @JvmStatic
    fun getFileTypeFromURI(uri: Uri, context: Context): String? {
        val contentResolver = context.contentResolver
        return contentResolver.getType(uri)?.split("/")?.toTypedArray()?.get(0)
    }

    @JvmStatic
    fun getFileExtentionFromURI(uri: Uri, context: Context): String? {
        val contentResolver = context.contentResolver
        val mimeTypeMap = MimeTypeMap.getSingleton()
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri))
    }
}