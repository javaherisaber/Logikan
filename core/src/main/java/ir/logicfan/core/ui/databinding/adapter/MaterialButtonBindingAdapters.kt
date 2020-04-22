/*
 * Copyright (C) Logicfan, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential
 * Written by Mahdi Javaheri Saber <m.javaherisaber@gmail.com>, Mohammad Hosein Abedini <mohammadhoseinabedini@gmail.com>  2020.
 */

package ir.logicfan.core.ui.databinding.adapter

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.google.android.material.button.MaterialButton

/**
 * Set background tint with colorInt
 */
@BindingAdapter("app:backgroundTint")
fun MaterialButton.setBackgroundTint(@ColorInt tint: Int) {
    this.backgroundTintList = ColorStateList.valueOf(tint)
}

/**
 * Show progress bar when user click on this button
 *
 * @param progressVisibility if true show progress, otherwise hide it
 * @param stagnateIcon icon to be used when progress is finished or it's not started yet
 * you can use this format for icon attribute:
 * app:icon="@{@drawable/finish_icon, default=@drawable/not_started_icon}
 */
@BindingAdapter(value = ["progressVisibility", "icon", "progressStrokeWidth"], requireAll = false)
fun MaterialButton.setCircularProgressDrawable(
    progressVisibility: Boolean?, stagnateIcon: Drawable, progressStrokeWidth: Int
) = progressVisibility?.let {
    if (it) {
        // show progress
        val circularProgressDrawable = CircularProgressDrawable(context).apply {
            setColorSchemeColors(iconTint.defaultColor)
            this.strokeWidth = with(progressStrokeWidth) { if (this == 0) 8f else this.toFloat() }
        }
        this.icon = circularProgressDrawable
        circularProgressDrawable.start()
        this.isClickable = false
    } else if (this.icon is CircularProgressDrawable) {
        // hide progress
        (this.icon as CircularProgressDrawable).stop()
        this.icon = stagnateIcon
        this.isClickable = true
    }
}