@file:JvmName("ImageViewBindingAdapters")

package ir.logicbase.logikan.ui.databinding.adapter

import android.content.res.ColorStateList
import android.widget.ImageView
import androidx.core.widget.ImageViewCompat
import androidx.databinding.BindingAdapter
import ir.logicbase.logikan.utils.inline.whenNotNullOrZero

/**
 * set tint if condition is true and clear tint otherwise
 */
@BindingAdapter(value = ["tintConditionTrue", "tintConditionFalse", "tintIf"], requireAll = true)
fun ImageView.setConditionalTint(tintConditionTrue: Int, tintConditionFalse: Int, condition: Boolean) {
    val tintList = if (condition) {
        ColorStateList.valueOf(tintConditionTrue)
    } else {
        whenNotNullOrZero(tintConditionFalse) {
            ColorStateList.valueOf(it)
        }
    }
    ImageViewCompat.setImageTintList(this, tintList)
}