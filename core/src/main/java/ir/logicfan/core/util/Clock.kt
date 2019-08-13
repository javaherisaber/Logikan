package ir.logicfan.core.util

import android.annotation.SuppressLint
import java.text.MessageFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Data holder to represent a clock
 */
data class Clock(
    var hour: Int = HOUR_MIN_VALUE,
    var minute: Int = MINUTE_MIN_VALUE,
    var second: Int = SECOND_MIN_VALUE
) {

    constructor(seconds: Int) : this(
        seconds.div(HOUR_DURATION_SECONDS),
        seconds.rem(HOUR_DURATION_SECONDS).div(MINUTE_DURATION_SECONDS),
        seconds.rem(MINUTE_DURATION_SECONDS)
    )

    init {
        validateArguments(hour, minute, second)
    }

    /**
     * Validate primary constructor inputs
     */
    @Throws(IllegalArgumentException::class)
    private fun validateArguments(hour: Int, minute: Int, second: Int) {
        if (hour > HOUR_MAX_VALUE || hour < HOUR_MIN_VALUE)
            throw IllegalArgumentException("hour = $hour is wrong, it must be from $HOUR_MIN_VALUE to $HOUR_MAX_VALUE")
        if (minute > MINUTE_MAX_VALUE || minute < MINUTE_MIN_VALUE)
            throw IllegalArgumentException("minute = $minute is wrong, it must be from $MINUTE_MIN_VALUE to $MINUTE_MAX_VALUE")
        if (second > SECOND_MAX_VALUE || second < SECOND_MIN_VALUE)
            throw IllegalArgumentException("second = $second is wrong, it must be from $SECOND_MIN_VALUE to $SECOND_MAX_VALUE")
    }

    /**
     * Count down clock values by one second
     */
    fun countDownSecond() {
        when {
            second > SECOND_MIN_VALUE -> {
                second--
            }
            minute > MINUTE_MIN_VALUE -> {
                minute--
                second = SECOND_MAX_VALUE
            }
            hour > HOUR_MIN_VALUE -> {
                hour--
                second = SECOND_MAX_VALUE
                minute = MINUTE_MAX_VALUE
            }
        }
    }

    /**
     * Count up clock values by one second
     */
    fun countUpSecond() {
        when {
            second < SECOND_MAX_VALUE -> {
                second++
            }
            minute < MINUTE_MAX_VALUE -> {
                minute++
                second = SECOND_MIN_VALUE
            }
            hour < HOUR_MAX_VALUE -> {
                hour++
                second = SECOND_MIN_VALUE
                minute = MINUTE_MIN_VALUE
            }
        }
    }

    /**
     * @return true if clock is 00:00:00, false otherwise
     */
    fun isMin(): Boolean = (second == SECOND_MIN_VALUE && minute == MINUTE_MIN_VALUE && hour == HOUR_MIN_VALUE)

    /**
     * Set minimum possible values
     */

    fun setMin() {
        second = SECOND_MIN_VALUE
        minute = MINUTE_MIN_VALUE
        hour = HOUR_MIN_VALUE
    }

    /**
     * @return true if clock is 23:59:59, false otherwise
     */
    fun isMax(): Boolean = (second == SECOND_MAX_VALUE && minute == MINUTE_MAX_VALUE && hour == HOUR_MAX_VALUE)

    /**
     * Set maximum possible values
     */
    fun setMax() {
        second = SECOND_MAX_VALUE
        minute = MINUTE_MAX_VALUE
        hour = HOUR_MAX_VALUE
    }

    private fun getClockLabelMinuteSecondType(): String {
        val labels = getLabels()
        return MessageFormat.format("{0}:{1}", labels.second, labels.third)
    }

    private fun getClockLabelHourMinuteType(): String {
        val labels = getLabels()
        return MessageFormat.format("{0}:{1}", labels.first, labels.second)
    }

    private fun getClockLabelHourMinuteSecondType(): String {
        val labels = getLabels()
        return MessageFormat.format("{0}:{1}:{2}", labels.first, labels.second, labels.third)
    }

    /**
     * Convert clock variables to labeled strings
     */
    private fun getLabels(): Triple<String, String, String> {
        var hourText = ""
        var minuteText = ""
        var secondText = ""
        if (hour < 10) {
            hourText += "0"
        }
        if (minute < 10) {
            minuteText += "0"
        }
        if (second < 10) {
            secondText += "0"
        }
        hourText += hour
        minuteText += minute
        secondText += second
        return Triple(hourText, minuteText, secondText)
    }

    /**
     * To be used by [getClockLabel] method to return suitable labeled clock
     */
    enum class ClockLabelType {
        HOUR_MINUTE,
        HOUR_MINUTE_SECOND,
        MINUTE_SECOND;
    }

    companion object {
        const val MINUTE_DURATION_SECONDS = 60
        const val HOUR_DURATION_SECONDS = 3600

        const val SECOND_MAX_VALUE = 59
        const val MINUTE_MAX_VALUE = 59
        const val HOUR_MAX_VALUE = 23

        const val SECOND_MIN_VALUE = 0
        const val MINUTE_MIN_VALUE = 0
        const val HOUR_MIN_VALUE = 0

        @JvmStatic
        fun getClockLabel(clock: Clock, type: ClockLabelType): String = when (type) {
            ClockLabelType.HOUR_MINUTE -> clock.getClockLabelHourMinuteType()
            ClockLabelType.HOUR_MINUTE_SECOND -> clock.getClockLabelHourMinuteSecondType()
            ClockLabelType.MINUTE_SECOND -> clock.getClockLabelMinuteSecondType()
        }

        /**
         * @param timestamp Unix Timestamp with this format : 1561783493
         * @return [Clock] object if timestamp is correct or null otherwise
         */
        @SuppressLint("SimpleDateFormat")
        @JvmStatic
        fun getClock(timestamp: String): Clock? = try {
            val date = Date(timestamp.toLong() * 1000L)
            val formatted = SimpleDateFormat("HH:mm:ss").format(date)
            val hourParts = formatted.split(":")
            Clock(hourParts[0].toInt(), hourParts[1].toInt(), hourParts[2].toInt())
        } catch (ex: NumberFormatException) {
            // string does not contain timestamp
            null
        }

    }
}