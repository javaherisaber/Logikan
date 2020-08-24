@file:Suppress("unused")

package ir.logicfan.core.ui.databinding.adapter

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.databinding.BindingAdapter
import ir.logicfan.core.R
import ir.logicfan.core.util.Clock
import ir.logicfan.core.util.calendar.JalaliCalendar
import ir.logicfan.core.util.extension.stripHtml
import ir.logicfan.core.util.extension.toSeparatedThousands
import java.text.MessageFormat

@BindingAdapter(value = ["text", "label"], requireAll = true)
fun TextView.textWithLabel(text: String?, label: String?) {
    this.text = label.plus(" : ").plus(text)
}

/**
 * Set text from R.string resource
 */
@BindingAdapter("android:text")
fun TextView.setTextWithResId(@StringRes resId: Int) {
    if (resId == 0) {
        this.text = null
        return
    }
    this.setText(resId)
}

@BindingAdapter("textHtml")
fun TextView.setTextHtml(resource: String) {
    this.text = resource.stripHtml()
}

@BindingAdapter("textHtml")
fun TextView.setTextHtml(@StringRes resource: Int) {
    if (resource == 0) {
        this.text = null
        return
    }
    this.text = context.getText(resource).toString().stripHtml()
}

@BindingAdapter(value = ["beforeParentheses", "insideParentheses"], requireAll = false)
fun TextView.setInsideParenthesesText(beforeParentheses: String?, insideParentheses: String?) = insideParentheses?.let {
    this.text = context.getString(R.string.core_all_textInsideParentheses, beforeParentheses, insideParentheses)
} ?: beforeParentheses?.let {
    this.text = beforeParentheses
}

@BindingAdapter("andSeparatedText")
fun TextView.setAndSeparatedText(data: Collection<Any>?) = data?.let {
    this.text = it.joinToString(separator = " " + context.getString(R.string.core_all_and) + " ")
}

@BindingAdapter("commaSeparatedText")
fun TextView.setCommaSeparatedText(data: Collection<Any>?) = data?.let {
    this.text = it.joinToString(separator = context.getString(R.string.core_all_comma) + " ")
}

/**
 * Set TextView's text with price separated thousands
 * @param priceLabel label being added as prefix text
 */
@SuppressLint("StringFormatInvalid")
@BindingAdapter(value = ["price", "priceLabel", "zeroValueMessage"], requireAll = false)
fun TextView.price(price: Long?, priceLabel: String?, zeroValueMessage: String?) {
    if (zeroValueMessage != null && (price == null || price == 0L)) {
        this.text = zeroValueMessage
        return
    }
    val priceSeparatedThousands = (price ?: 0).toSeparatedThousands()
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