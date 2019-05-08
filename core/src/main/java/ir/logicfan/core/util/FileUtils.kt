package ir.logicfan.core.util

import android.content.Context
import java.io.IOException

object FileUtils {

    @JvmStatic
    @Throws(IOException::class)
    fun readTextFileFromAssets(context: Context, path: String): String {
        return context.resources.assets.open(path).bufferedReader().use {
            it.readText()
        }
    }
}
