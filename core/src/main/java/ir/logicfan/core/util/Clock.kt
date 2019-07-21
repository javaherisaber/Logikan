package ir.logicfan.core.util

import android.annotation.SuppressLint
import java.text.MessageFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Data holder to represent a clock
 */
data class Clock(var hour: Int = 0, var minute: Int = 0, var second: Int = 0) {

    constructor(seconds: Int) : this(seconds.div(60).div(60), seconds.div(60), seconds.rem(60))

    init {
        validateArguments(hour, minute, second)
    }

    /**
     * Validate primary constructor inputs
     */
    @Throws(IllegalArgumentException::class)
    private fun validateArguments(hour: Int, minute: Int, second: Int) {
        if (hour >= 24 || hour < 0)
            throw IllegalArgumentException("Wrong value for hour, it must be from 0 to 23")
        if (minute >= 60 || minute < 0)
            throw IllegalArgumentException("Wrong value for minute, it must be from 0 to 59")
        if (second >= 60 || second < 0)
            throw IllegalArgumentException("Wrong value for hour, it must be from 0 to 59")
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
        HOUR_MINUTE, HOUR_MINUTE_SECOND, MINUTE_SECOND
    }

    companion object {
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