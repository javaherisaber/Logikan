package ir.logicbase.logikan.ui.util.extension

import android.content.Intent

/**
 * require data Uri for receiver intent
 */
fun Intent.requireData() = this.data ?: error("No data found for intent")