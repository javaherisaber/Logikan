package ir.logicfan.core.util

object StringUtils {

    @Deprecated("It is ineffective function", ReplaceWith(
        "number.toSeparatedThousands()",
        "ir.logicfan.core.util.extension.toSeparatedThousands"
    ))
    @JvmStatic
    fun numberToSeparatedThousands(number: Long): String = String.format("%,d", number)
}