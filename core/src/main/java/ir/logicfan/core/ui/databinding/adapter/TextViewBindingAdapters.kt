package ir.logicfan.core.ui.databinding.adapter

import android.annotation.SuppressLint
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

@BindingAdapter("android:text")
fun TextView.setTextWithResId(resId: Int) {
    if (resId == 0) {
        return
    }
    this.setText(resId)
}

@BindingAdapter("andSeparatedText")
fun TextView.setAndSeparatedText(data: Collection<Any>?) = data?.let {
    this.text = it.joinToString(separator = " " + context.getString(R.string.core_all_and) + " ")
}

/**
 * Set TextView's text with price separated thousands
 * @param priceLabel label being added as prefix text
 */
@SuppressLint("StringFormatInvalid")
@BindingAdapter(value = ["price", "priceLabel", "hasAvailability"], requireAll = false)
fun TextView.price(price: Long?, priceLabel: String?, hasAvailability: Boolean) {
    if (hasAvailability && (price == null || price == 0L)) {
        this.text = context.getString(R.string.core_all_unavailable)
        return
    }
    val priceSeparatedThousands = StringUtils.numberToSeparatedThousands(price ?: 0)
    var resultText = ""
    priceLabel?.let {
        resultText += "$priceLabel "
    }
    resultText += context.getString(R.string.core_all_price_with, priceSeparatedThousands)
    this.text = resultText
}

/**
 * Set TextView's text with Jalali date extracted from Unix formatted timestamp
 *
 * @param timestamp Unix epoch timestamp
 */
@BindingAdapter(value = ["jalaliUnixDateLabel", "jalaliLabelType", "label"], requireAll = false)
fun TextView.jalaliUnixDateLabel(timestamp: Long?, jalaliLabelType: JalaliCalendar.JalaliLabelType?, label: String?) =
    timestamp?.let {
        val jalaliCalendar = JalaliCalendar.getJalaliCalendarFromUnixTimestamp(timestamp)
        var resultText = ""
        label?.let {
            resultText += "$it "
        }
        resultText += JalaliCalendar.getDateLabel(
            jalaliCalendar,
            jalaliLabelType ?: JalaliCalendar.JalaliLabelType.DIGIT
        )
        this.text = resultText
    }

/**
 * Set TextView's text with Jalali date extracted from ISO formatted timestamp
 *
 * @param timestamp ISO timestamp with this format yyyy-MM-dd HH:mm:ss
 */
@BindingAdapter(value = ["jalaliIsoDateLabel", "jalaliLabelType", "label"], requireAll = false)
fun TextView.jalaliIsoDateLabel(timestamp: String?, jalaliLabelType: JalaliCalendar.JalaliLabelType?, label: String?) =
    timestamp?.let {
        val jalaliCalendar = JalaliCalendar.getJalaliCalendarFromIsoTimestamp(timestamp)
        var resultText = ""
        label?.let {
            resultText += "$it "
        }
        resultText += JalaliCalendar.getDateLabel(
            jalaliCalendar,
            jalaliLabelType ?: JalaliCalendar.JalaliLabelType.DIGIT
        )
        this.text = resultText
    }

/**
 * Set TextView's text with Jalali datetime extracted from Unix timestamp
 *
 * @param timestamp Unix epoch timestamp
 */
@BindingAdapter(value = ["jalaliUnixDateTimeLabel", "jalaliLabelType", "clockLabelType", "label"], requireAll = false)
fun TextView.jalaliUnixDateTimeLabel(
    timestamp: Long?,
    jalaliLabelType: JalaliCalendar.JalaliLabelType?,
    clockLabelType: Clock.ClockLabelType?,
    label: String?
) = timestamp?.let {
    val jalaliCalendar = JalaliCalendar.getJalaliCalendarFromUnixTimestamp(timestamp)
    var labelText = ""
    label?.let {
        labelText += it
    }
    this.setJalaliDateTimeLabel(
        labelText,
        jalaliCalendar,
        jalaliLabelType ?: JalaliCalendar.JalaliLabelType.DIGIT,
        clockLabelType ?: Clock.ClockLabelType.HOUR_MINUTE
    )
}

/**
 * Set TextView's text with Jalali datetime extracted from ISO formatted timestamp
 *
 * @param timestamp ISO timestamp with this format yyyy-MM-dd HH:mm:ss
 */
@BindingAdapter(value = ["jalaliIsoDateTimeLabel", "jalaliLabelType", "clockLabelType", "label"], requireAll = false)
fun TextView.jalaliIsoDateTimeLabel(
    timestamp: String?,
    jalaliLabelType: JalaliCalendar.JalaliLabelType?,
    clockLabelType: Clock.ClockLabelType?,
    label: String?
) = timestamp?.let {
    val jalaliCalendar = JalaliCalendar.getJalaliCalendarFromIsoTimestamp(timestamp)
    var labelText = ""
    label?.let {
        labelText += it
    }
    this.setJalaliDateTimeLabel(
        labelText,
        jalaliCalendar,
        jalaliLabelType ?: JalaliCalendar.JalaliLabelType.DIGIT,
        clockLabelType ?: Clock.ClockLabelType.HOUR_MINUTE
    )
}

private fun TextView.setJalaliDateTimeLabel(
    label: String,
    jalaliCalendar: JalaliCalendar,
    jalaliLabelType: JalaliCalendar.JalaliLabelType,
    clockLabelType: Clock.ClockLabelType
) {
    val hourText = this.context.getString(R.string.core_all_hour)
    val timeLabel = Clock.getClockLabel(jalaliCalendar.clock, clockLabelType)
    val dateLabel = JalaliCalendar.getDateLabel(jalaliCalendar, jalaliLabelType)
    this.text = MessageFormat.format(
        "{0} {1} {2} {3}",
        label, dateLabel, hourText, timeLabel
    )
}