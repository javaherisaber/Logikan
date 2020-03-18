/*
 * Copyright (C) Logicfan, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential
 * Written by Mahdi Javaheri Saber <m.javaherisaber@gmail.com>, Mohammad Hosein Abedini <mohammadhoseinabedini@gmail.com>  2020.
 */

package ir.logicfan.core.util

object StringUtils {

    @Deprecated("It is ineffective function", ReplaceWith(
        "number.toSeparatedThousands()",
        "ir.logicfan.core.util.extension.toSeparatedThousands"
    ))
    @JvmStatic
    fun numberToSeparatedThousands(number: Long): String = String.format("%,d", number)
}