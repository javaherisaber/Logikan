package ir.logicbase.logikan.data.util

import android.content.Context
import java.io.IOException

object FileUtils {

    @JvmStatic
    @Throws(IOException::class)
    fun readTextFileFromAssets(context: Context, path: String): String =
        context.resources.assets.open(path).bufferedReader().use {
            it.readText()
        }
}
