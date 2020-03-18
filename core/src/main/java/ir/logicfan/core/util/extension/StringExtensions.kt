/*
 * Copyright (C) Logicfan, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential
 * Written by Mahdi Javaheri Saber <m.javaherisaber@gmail.com>, Mohammad Hosein Abedini <mohammadhoseinabedini@gmail.com>  2020.
 */

package ir.logicfan.core.util.extension

import android.os.Build
import android.text.Html
import android.text.Spanned

fun String?.stripHtml(): Spanned =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this ?: "", Html.FROM_HTML_MODE_COMPACT)
    } else @Suppress("DEPRECATION") {
        Html.fromHtml(this ?: "")
    }

fun String.isValidIranMobile(): Boolean = this.matches("(0(9[0-9]{9}))|(\\+98(9[0-9]{9}))".toRegex())

/**
 * Add Bearer prefix to token string
 */
fun String.toBearerToken(): String = "Bearer $this"