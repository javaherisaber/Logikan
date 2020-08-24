package ir.logicfan.core.util.extension

import ir.logicfan.core.util.NumberTranscriptUtil
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