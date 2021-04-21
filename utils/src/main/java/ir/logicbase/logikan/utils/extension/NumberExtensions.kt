package ir.logicbase.logikan.utils.extension

import ir.logicbase.logikan.utils.NumberTranscriptUtil
import java.util.*

/**
 * @return number with zero tail if less than 10 -> eg. 07
 */
fun Int.toZeroTail(): String = if (this < 10) "0$this" else this.toString()

/**
 * @return number with thousands separated with ','
 */
fun Number.toSeparatedThousands(): String = String.format(Locale.US, "%,d", this)

/**
 * Convert number to transcript representation
 */
fun Number.transcript(): String = NumberTranscriptUtil.transcriptNumber(this.toLong())

fun Number?.isNullOrZero(): Boolean = this?.toDouble() == 0.0 || this == null

fun Number?.isNotNullOrZero(): Boolean = this?.toDouble() != 0.0 && this != null