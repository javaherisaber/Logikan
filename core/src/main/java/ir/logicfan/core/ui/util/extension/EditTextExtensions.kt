package ir.logicfan.core.ui.util.extension

import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import ir.logicfan.core.util.extension.stripThousandsSeparator
import ir.logicfan.core.util.extension.toSeparatedThousands
import ir.logicfan.core.util.extension.transcript

/**
 * When del key pressed if text input is empty, focus returns to previous input
 */
fun EditText.addEmptyInputDelKeyListener(previous: EditText) {
    setOnKeyListener { _, keyCode, _ ->
        if (this.text.toString().isEmpty() && keyCode == KeyEvent.KEYCODE_DEL) {
            previous.requestFocus()
        }
        return@setOnKeyListener false
    }
}

/**
 * Separate thousands of editText value
 */
fun EditText.addNumberThousandsSeparator(textInputLayout: TextInputLayout? = null) = this.addTextChangedListener(
    object : TextWatcher {
        override fun afterTextChanged(editable: Editable) {
            if (editable.isEmpty()) {
                textInputLayout?.helperText = ""
                return
            }
            val text = editable.toString()
            val commaClearedText = text.stripThousandsSeparator()
            val number = commaClearedText.toLong()
            removeTextChangedListener(this)
            textInputLayout?.helperText = number.transcript()
            setText(number.toSeparatedThousands())
            setSelection(this@addNumberThousandsSeparator.text.length)
            addTextChangedListener(this)
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })