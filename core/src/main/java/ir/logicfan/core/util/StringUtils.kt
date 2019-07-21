package ir.logicfan.core.util

object StringUtils {

    @JvmStatic
    fun numberToSeparatedThousands(number: Long): String = String.format("%,d", number)
}