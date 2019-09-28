package ir.logicfan.core.util

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.net.Uri
import android.provider.MediaStore
import android.util.Base64
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import java.io.ByteArrayOutputStream


fun getBitmapFromURL(url: String, context: Context, operator: (bitmap: Bitmap, mimeType: String) -> Unit) {
    Glide.with(context)
        .asBitmap()
        .load(url)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                getMimeTypeFromURL(url)?.let { mimeType -> operator(resource, mimeType) }
            }

            override fun onLoadCleared(placeholder: Drawable?) {
            }
        })
}

fun getBitmapFromURI(uri: Uri, context: Context): Bitmap {
    if (getFileTypeFromURI(uri, context) == "image") {
        return BitmapFactory.decodeStream(context.contentResolver?.openInputStream(uri))
    } else {
        throw IllegalArgumentException()
    }
}

fun showImagePickFromFileManagerIntent(fragment: Fragment, requestCode: Int, chooserTitle: String) {
    val getIntent = Intent(Intent.ACTION_GET_CONTENT)
    getIntent.type = "image/*"
    val pickIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
    pickIntent.type = "image/*"
    val chooserIntent = Intent.createChooser(getIntent, chooserTitle)
    chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(pickIntent))
    fragment.startActivityForResult(chooserIntent, requestCode)
}

fun convertBitmapToBase64(bitmap: Bitmap): String {
    val byteArrayOutputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream)
    val byteArray = byteArrayOutputStream.toByteArray()
    return Base64.encodeToString(byteArray, Base64.DEFAULT)
}

