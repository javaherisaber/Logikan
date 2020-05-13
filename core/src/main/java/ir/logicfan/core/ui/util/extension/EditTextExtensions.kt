package ir.logicfan.core.ui.util.extension

import android.view.KeyEvent
import android.widget.EditText

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