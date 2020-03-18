/*
 * Copyright (C) Logicfan, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential
 * Written by Mahdi Javaheri Saber <m.javaherisaber@gmail.com>, Mohammad Hosein Abedini <mohammadhoseinabedini@gmail.com>  2020.
 */

package ir.logicfan.core.util.extension

/**
 * Generate comma separated values from your Collection of integer
 * eg. 1,3,5,7,8
 */
fun <T> Collection<T>.createCommaSeparatedValues(): String = this.joinToString(separator = ",") { it.toString() }

fun <T : Any> Collection<T?>.whenAllNotNull(block: () -> Unit) {
    if (this.all { it != null }) {
        block()
    }
}