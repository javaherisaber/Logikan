package ir.logicbase.core.ui.databinding.adapter

import android.content.res.ColorStateList
import android.widget.ImageView
import androidx.core.widget.ImageViewCompat
import androidx.databinding.BindingAdapter

/**
 * set tint if condition is true and clear tint otherwise
 */
@BindingAdapter(value = ["android:tint", "tintCondition"], requireAll = true)
fun ImageView.setConditionalTint(tint: Int, condition: Boolean) {
    val tintList = if (condition) {
        ColorStateList.valueOf(tint)
    } else {
        null
    }
    ImageViewCompat.setImageTintList(this, tintList)
}