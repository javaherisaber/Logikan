package ir.logicbase.logikan.ui.databinding.converter

import androidx.databinding.InverseMethod

@InverseMethod("convertNumberToShebaCode")
fun convertShebaCodeToNumber(shebaCode: String?): String? {
    return shebaCode?.removePrefix("IR")
}

fun convertNumberToShebaCode(shebaNumberCode: String): String {
    return "IR$shebaNumberCode"
}
