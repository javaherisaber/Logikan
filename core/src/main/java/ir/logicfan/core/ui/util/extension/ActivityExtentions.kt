/*
 * Copyright (C) Logicfan, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential
 * Written by Mahdi Javaheri Saber <m.javaherisaber@gmail.com>, Mohammad Hosein Abedini <mohammadhoseinabedini@gmail.com>  2020.
 */

package ir.logicfan.core.ui.util.extension

import android.app.Activity
import android.widget.Toast
import ir.logicfan.core.R

fun Activity.showTryAgainErrorToast() {
    Toast.makeText(this, R.string.core_error_try_again, Toast.LENGTH_LONG).show()
}