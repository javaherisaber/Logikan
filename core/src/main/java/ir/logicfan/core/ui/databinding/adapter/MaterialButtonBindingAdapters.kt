package ir.logicfan.core.ui.databinding.adapter

import android.content.res.ColorStateList
import androidx.annotation.ColorInt
import androidx.databinding.BindingAdapter
import com.google.android.material.button.MaterialButton

/**
 * Set background tint with colorInt
 */
@BindingAdapter("app:backgroundTint")
fun MaterialButton.setBackgroundTint(@ColorInt tint: Int) {
    this.backgroundTintList = ColorStateList.valueOf(tint)
}