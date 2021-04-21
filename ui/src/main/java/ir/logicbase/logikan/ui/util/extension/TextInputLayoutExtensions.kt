package ir.logicbase.logikan.ui.util.extension

import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import ir.logicbase.logikan.ui.util.extension.addNumberThousandsSeparator

/**
 * Assign number transcript as helper message to text input layout
 */
fun TextInputLayout.addNumberTranscriptHelper() {
    this.isHelperTextEnabled = true
    val editText: EditText = this.editText ?: return
    editText.addNumberThousandsSeparator(this)
}