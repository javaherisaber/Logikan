/*
 * Copyright (C) Logicfan, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential
 * Written by Mahdi Javaheri Saber <m.javaherisaber@gmail.com>, Mohammad Hosein Abedini <mohammadhoseinabedini@gmail.com>  2020.
 */

package ir.logicfan.core.ui.widget.text

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