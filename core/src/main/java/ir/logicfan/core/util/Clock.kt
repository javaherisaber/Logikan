package ir.logicfan.core.util

import android.annotation.SuppressLint
import ir.logicfan.core.util.extension.toZeroTail
import java.text.MessageFormat

/**
 * Data holder to represent a clock
 */
data class Clock(
    var hour: Int = HOUR_MIN_VALUE,
    var minute: Int = MINUTE_MIN_VALUE,
    var second: Int = SECOND_MIN_VALUE
) : Comparable<Clock> {

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
        require(!(hour > HOUR_MAX_VALUE || hour < HOUR_MIN_VALUE)) {
            "hour = $hour is wrong, it must be from $HOUR_MIN_VALUE to $HOUR_MAX_VALUE"
        }
        require(!(minute > MINUTE_MAX_VALUE || minute < MINUTE_MIN_VALUE)) {
            "minute = $minute is wrong, it must be from $MINUTE_MIN_VALUE to $MINUTE_MAX_VALUE"
        }
        require(!(second > SECOND_MAX_VALUE || second < SECOND_MIN_VALUE)) {
            "second = $second is wrong, it must be from $SECOND_MIN_VALUE to $SECOND_MAX_VALUE"
        }
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
    private fun getLabels(): Triple<String, String, String> = Triple(
        hour.toZeroTail(), minute.toZeroTail(), second.toZeroTail()
    )

    override fun toString(): String = getClockLabelHourMinuteSecondType()

    override fun compareTo(other: Clock): Int = when {
        hour != other.hour -> hour - other.hour
        minute != other.minute -> minute - other.minute
        else -> second - other.second
    }

    /**
     * To be used by [getClockLabel] method to return suitable labeled clock
     */
    enum class ClockLabelType {
        HOUR_MINUTE, // 20:30
        HOUR_MINUTE_SECOND, // 20:30:45
        MINUTE_SECOND; // 15:00
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
         * @return Clock with given format (15:30:45)
         */
        @SuppressLint("SimpleDateFormat")
        @Throws(IllegalArgumentException::class)
        fun getClock(format: String): Clock {
            val hourParts = format.split(":")
            return Clock(hourParts[0].toInt(), hourParts[1].toInt(), hourParts[2].toInt())
        }
    }
}