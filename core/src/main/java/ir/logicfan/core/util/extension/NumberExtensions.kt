package ir.logicfan.core.util.extension

/**
 * @return number with zero tail if less than 10 -> eg. 07
 */
fun Int.toZeroTail(): String = if (this < 10) "0$this" else this.toString()

fun Number.toSeparatedThousands(): String = String.format("%,d", this)