package ir.logicfan.core.ui.databinding.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import ir.logicfan.core.R
import ir.logicfan.core.util.Clock
import ir.logicfan.core.util.calendar.JalaliCalendar
import java.text.MessageFormat

object TextViewBindingAdapters {

    /**
     * Set text with requested type
     *
     * @param timestamp Unix epoch timestamp
     * @param jalaliLabelType defined inside JalaliCalendar class
     */
    @BindingAdapter(value = ["jalaliDateLabel", "jalaliLabelType"], requireAll = true)
    @JvmStatic
    fun jalaliDateLabel(textView: TextView, timestamp: String?, jalaliLabelType: JalaliCalendar.JalaliLabelType) {
        if (timestamp == null) {
            return
        }
        val jalaliCalendar = JalaliCalendar.getJalaliCalendar(timestamp)
        jalaliCalendar?.let {
            textView.text = JalaliCalendar.getDateLabel(it, jalaliLabelType)
        }
    }

    /**
     * Set text with requested type
     *
     * @param timestamp Unix epoch timestamp
     * @param jalaliLabelType defined inside JalaliCalendar class
     * @param clockLabelType defined inside Clock class
     */
    @BindingAdapter(value = ["jalaliDateTimeLabel", "jalaliLabelType", "clockLabelType"], requireAll = true)
    @JvmStatic
    fun jalaliDateTimeLabel(
        textView: TextView,
        timestamp: String?,
        jalaliLabelType: JalaliCalendar.JalaliLabelType,
        clockLabelType: Clock.ClockLabelType
    ) {
        if (timestamp == null) {
            return
        }
        val jalaliCalendar = JalaliCalendar.getJalaliCalendar(timestamp)
        jalaliCalendar?.let {
            val hourText = textView.context.getString(R.string.core_all_hour)
            val timeLabel = Clock.getClockLabel(jalaliCalendar.clock, clockLabelType)
            val dateLabel = JalaliCalendar.getDateLabel(jalaliCalendar, jalaliLabelType)
            textView.text = MessageFormat.format(
                "{0} {1} {2}",
                dateLabel, hourText, timeLabel
            )
        }
    }
}