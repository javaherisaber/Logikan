/*
 * Copyright (C) Logicfan, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential
 * Written by Mahdi Javaheri Saber <m.javaherisaber@gmail.com>, Mohammad Hosein Abedini <mohammadhoseinabedini@gmail.com>  2020.
 */

package ir.logicfan.core.ui.databinding.converter

import androidx.databinding.InverseMethod

@InverseMethod("convertNumberToShebaCode")
fun convertShebaCodeToNumber(shebaCode: String?): String? {
    return shebaCode?.removePrefix("IR")
}

fun convertNumberToShebaCode(shebaNumberCode: String): String {
    return "IR$shebaNumberCode"
}
