package ir.logicbase.core.ui.databinding.adapter

import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ir.logicbase.core.util.inline.whenNotNullOrZero

@BindingAdapter(value = ["progressVisibility", "progressIcon", "progressStrokeWidth", "progressTint"], requireAll = false)
fun FloatingActionButton.setCircularProgressDrawable(
    progressVisibility: Boolean?, stagnateIcon: Drawable, progressStrokeWidth: Int, @ColorInt progressTint: Int?
) = progressVisibility?.let {
    if (it) {
        // show progress
        val circularProgressDrawable = CircularProgressDrawable(context).apply {
            whenNotNullOrZero(progressTint) { tint ->
                setColorSchemeColors(tint)
            } ?: run { setColorSchemeColors(Color.WHITE) }
            this.strokeWidth = with(progressStrokeWidth) { if (this == 0) 8f else this.toFloat() }
        }
        this.setImageDrawable(circularProgressDrawable)
        circularProgressDrawable.start()
        this.isClickable = false
    } else if (this.drawable is CircularProgressDrawable) {
        // hide progress
        (this.drawable as CircularProgressDrawable).stop()
        this.setImageDrawable(stagnateIcon)
        this.isClickable = true
    }
}