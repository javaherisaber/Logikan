package ir.logicfan.core.ui.databinding.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import ir.logicfan.core.R
import ir.logicfan.core.util.Clock
import ir.logicfan.core.util.StringUtils
import ir.logicfan.core.util.calendar.JalaliCalendar
import java.text.MessageFormat

@BindingAdapter(value = ["text", "label"], requireAll = true)
fun TextView.textWithLabel(text: String?, label: String?) {
    this.text = label.plus(" : ").plus(text)
}

object TextViewBindingAdapters {
    /**
     * Set TextView's text with price separated thousands
     * @param priceLabel label being added as prefix text
     */
    @BindingAdapter(value = ["price", "priceLabel"], requireAll = false)
    @JvmStatic
    fun price(textView: TextView, price: Long?, priceLabel: String?) {
        val context = textView.context
        val priceSeparatedThousands = StringUtils.numberToSeparatedThousands(price ?: 0)
        var resultText = ""
        priceLabel?.let {
            resultText += "$priceLabel "
        }
        resultText += context.getString(R.string.core_all_price_with, priceSeparatedThousands)
        textView.text = resultText
    }

    /**
     * Set TextView's text with Jalali date extracted from Unix formatted timestamp
     *
     * @param timestamp Unix epoch timestamp
     */
    @BindingAdapter(value = ["jalaliUnixDateLabel", "jalaliLabelType"], requireAll = true)
    @JvmStatic
    fun jalaliUnixDateLabel(textView: TextView, timestamp: Long?, jalaliLabelType: JalaliCalendar.JalaliLabelType) {
        if (timestamp == null) {
            return
        }
        val jalaliCalendar = JalaliCalendar.getJalaliCalendarFromUnixTimestamp(timestamp)
        setJalaliDateLabel(textView, jalaliCalendar, jalaliLabelType)
    }

    /**
     * Set TextView's text with Jalali date extracted from ISO formatted timestamp
     *
     * @param timestamp ISO timestamp with this format yyyy-MM-dd HH:mm:ss
     */
    @BindingAdapter(value = ["jalaliIsoDateLabel", "jalaliLabelType"], requireAll = true)
    @JvmStatic
    fun jalaliIsoDateLabel(textView: TextView, timestamp: String?, jalaliLabelType: JalaliCalendar.JalaliLabelType) {
        if (timestamp == null) {
            return
        }
        val jalaliCalendar = JalaliCalendar.getJalaliCalendarFromIsoTimestamp(timestamp)
        setJalaliDateLabel(textView, jalaliCalendar, jalaliLabelType)
    }

    private fun setJalaliDateLabel(
        textView: TextView,
        jalaliCalendar: JalaliCalendar?,
        jalaliLabelType: JalaliCalendar.JalaliLabelType
    ) {
        jalaliCalendar?.let {
            textView.text = JalaliCalendar.getDateLabel(it, jalaliLabelType)
        }
    }

    /**
     * Set TextView's text with Jalali datetime extracted from Unix timestamp
     *
     * @param timestamp Unix epoch timestamp
     */
    @BindingAdapter(value = ["jalaliUnixDateTimeLabel", "jalaliLabelType", "clockLabelType"], requireAll = true)
    @JvmStatic
    fun jalaliUnixDateTimeLabel(
        textView: TextView,
        timestamp: Long?,
        jalaliLabelType: JalaliCalendar.JalaliLabelType,
        clockLabelType: Clock.ClockLabelType
    ) {
        if (timestamp == null) {
            return
        }
        val jalaliCalendar = JalaliCalendar.getJalaliCalendarFromUnixTimestamp(timestamp)
        setJalaliDateTimeLabel(textView, jalaliCalendar, jalaliLabelType, clockLabelType)
    }

    /**
     * Set TextView's text with Jalali datetime extracted from ISO formatted timestamp
     *
     * @param timestamp ISO timestamp with this format yyyy-MM-dd HH:mm:ss
     */
    @BindingAdapter(value = ["jalaliIsoDateTimeLabel", "jalaliLabelType", "clockLabelType"], requireAll = true)
    @JvmStatic
    fun jalaliIsoDateTimeLabel(
        textView: TextView,
        timestamp: String?,
        jalaliLabelType: JalaliCalendar.JalaliLabelType,
        clockLabelType: Clock.ClockLabelType
    ) {
        if (timestamp == null) {
            return
        }
        val jalaliCalendar = JalaliCalendar.getJalaliCalendarFromIsoTimestamp(timestamp)
        setJalaliDateTimeLabel(textView, jalaliCalendar, jalaliLabelType, clockLabelType)
    }

    private fun setJalaliDateTimeLabel(
        textView: TextView,
        jalaliCalendar: JalaliCalendar?,
        jalaliLabelType: JalaliCalendar.JalaliLabelType,
        clockLabelType: Clock.ClockLabelType
    ) {
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