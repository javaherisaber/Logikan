package ir.logicbase.logikan.utils

object NumberTranscriptUtil {

    fun transcriptNumber(number: Long): String {
        if (number == 0L) {
            return ZERO
        }
        var result: String
        val (base, divisor) = baseToDivisor(number)
        val real = number / divisor
        var reminder = number % divisor
        when {
            (real == 0L || real == 1L) && divisor == TEN -> {
                result = UNARY[number.toInt() - 1]
                reminder = 0L
            }
            (real != 0L && real != 1L) && divisor == TEN -> {
                result = DECIMALS[real.toInt()] ?: error("Can't parse number $real")
            }
            real == 0L -> {
                result = transcriptNumber(number)
            }
            real != 0L && divisor == ONE_HUNDRED -> {
                result = HUNDREDS[real.toInt() - 1]
            }
            else -> result = transcriptNumber(real) + " $base"
        }
        when {
            reminder != 0L && divisor == TEN -> result += " $SEPARATOR " + UNARY[reminder.toInt() - 1]
            reminder != 0L -> result += " $SEPARATOR " + transcriptNumber(reminder)
        }
        return result
    }

    private fun baseToDivisor(number: Long): Pair<String?, Int> = when {
        number / ONE_BILLION != 0L -> BILLION to ONE_BILLION
        number / ONE_MILLION != 0L -> MILLION to ONE_MILLION
        number / ONE_THOUSAND != 0L -> THOUSAND to ONE_THOUSAND
        number / ONE_HUNDRED != 0L -> null to ONE_HUNDRED
        else -> null to TEN
    }

    private val UNARY = listOf(
        "یک", "دو", "سه", "چهار", "پنج", "شش", "هفت", "هشت", "نه", "ده",
        "یازده", "دوازده", "سیزده", "چهارده", "پانزده", "شانزده", "هفده", "هجده", "نوزده"
    )
    private val DECIMALS = mapOf(
        2 to "بیست", 3 to "سی", 4 to "چهل", 5 to "پنجاه", 6 to "شصت",
        7 to "هفتاد", 8 to "هشتاد", 9 to "نود"
    )
    private val HUNDREDS = listOf("صد", "دویست", "سیصد", "چهارصد", "پانصد", "ششصد", "هفتصد", "هشتصد", "نهصد")
    private const val ZERO = "صفر"
    private const val TEN = 10
    private const val ONE_HUNDRED = 100
    private const val THOUSAND = "هزار"
    private const val ONE_THOUSAND = 1000
    private const val MILLION = "میلیون"
    private const val ONE_MILLION = 1000000
    private const val BILLION = "میلیارد"
    private const val ONE_BILLION = 1000000000
    private const val SEPARATOR = "و"
}