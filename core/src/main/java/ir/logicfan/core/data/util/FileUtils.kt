/*
 * Copyright (C) Logicfan, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential
 * Written by Mahdi Javaheri Saber <m.javaherisaber@gmail.com>, Mohammad Hosein Abedini <mohammadhoseinabedini@gmail.com>  2020.
 */

package ir.logicfan.core.data.util

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
