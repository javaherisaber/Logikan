/*
 * Copyright (C) Logicfan, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential
 * Written by Mahdi Javaheri Saber <m.javaherisaber@gmail.com>, Mohammad Hosein Abedini <mohammadhoseinabedini@gmail.com>  2020.
 */

package ir.logicfan.core.ui.databinding.adapter

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