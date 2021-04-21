package ir.logicbase.logikan.ui.text

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

/**
 * Move between chained edit texts when user put an input
 *
 * @property maxLength maxLength of your edit text
 */
class EditTextChainedTextWatcher(
    private val maxLength: Int,
    private val previousEditText: EditText,
    private val nextEditText: EditText
) : TextWatcher {

    override fun afterTextChanged(editable: Editable) {
        val text = editable.toString()
        if (text.length == maxLength) {
            nextEditText.requestFocus()
        } else if (text.isEmpty()) {
            previousEditText.requestFocus()
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
}