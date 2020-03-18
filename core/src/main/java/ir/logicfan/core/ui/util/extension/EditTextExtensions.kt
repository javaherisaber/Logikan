/*
 * Copyright (C) Logicfan, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential
 * Written by Mahdi Javaheri Saber <m.javaherisaber@gmail.com>, Mohammad Hosein Abedini <mohammadhoseinabedini@gmail.com>  2020.
 */

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