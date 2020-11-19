@file:Suppress("unused")

package ir.logicbase.core.ui.databinding.adapter

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.databinding.BindingAdapter
import ir.logicbase.core.R
import ir.logicbase.core.util.extension.stripHtml
import ir.logicbase.core.util.extension.toSeparatedThousands

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