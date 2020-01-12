package ir.logicfan.core.ui.databinding.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import ir.logicfan.core.R

/**
 * Show progress bar when user click on this button
 *
 * @param progressVisibility if true show progress, otherwise hide it
 * @param stagnateIcon icon to be used when progress is finished or it's not started yet
 * you can use this format for icon attribute:
 * app:icon="@{@drawable/finish_icon, default=@drawable/not_started_icon}
 * @param progressStrokeColor stroke color of progress bar
 * @param progressStrokeWidth stroke width of progress bar as pixel
 */
@BindingAdapter(
    value = ["progressVisibility", "icon", "progressStrokeColor", "progressStrokeWidth"],
    requireAll = false
)
fun ExtendedFloatingActionButton.setCircularProgressDrawable(
    progressVisibility: Boolean?,
    stagnateIcon: Drawable?,
    @ColorRes progressStrokeColor: Int?,
    progressStrokeWidth: Int?
) = progressVisibility?.let {
    if (it) {
        // show progress
        val circularProgressDrawable = createCircularProgressDrawable(context, progressStrokeColor, progressStrokeWidth)
        this.icon = circularProgressDrawable
        circularProgressDrawable.start()
        this.isClickable = false
    } else {
        // hide progress
        if (this.icon is CircularProgressDrawable) {
            (this.icon as CircularProgressDrawable).stop()
        }
        this.icon = stagnateIcon
        this.isClickable = true
    }
}

/**
 * create a circular progress drawable
 * you can use this drawable in any widget
 *
 * @param progressStrokeColor stroke color of progress bar
 * @param progressStrokeWidth stroke width of progress bar as pixel
 */
fun createCircularProgressDrawable(context: Context, @ColorRes progressStrokeColor: Int?, progressStrokeWidth: Int?) =
    CircularProgressDrawable(context).apply {
        setColorSchemeColors(ContextCompat.getColor(context, progressStrokeColor ?: R.color.core_colorWhite))
        strokeWidth = progressStrokeWidth?.toFloat() ?: 8f
    }