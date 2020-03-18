/*
 * Copyright (C) Logicfan, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential
 * Written by Mahdi Javaheri Saber <m.javaherisaber@gmail.com>, Mohammad Hosein Abedini <mohammadhoseinabedini@gmail.com>  2020.
 */

package ir.logicfan.core.util.extension

/**
 * @return number with zero tail if less than 10 -> eg. 07
 */
fun Int.toZeroTail(): String = if (this < 10) "0$this" else this.toString()

fun Number.toSeparatedThousands(): String = String.format("%,d", this)