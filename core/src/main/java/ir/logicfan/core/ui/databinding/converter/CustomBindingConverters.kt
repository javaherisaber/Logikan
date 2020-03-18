/*
 * Copyright (C) Logicfan, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential
 * Written by Mahdi Javaheri Saber <m.javaherisaber@gmail.com>, Mohammad Hosein Abedini <mohammadhoseinabedini@gmail.com>  2020.
 */

package ir.logicfan.core.ui.databinding.converter

import android.view.View
import androidx.databinding.BindingConversion

/**
 * The number of likes is an integer and the visibility attribute takes an integer
 * (VISIBLE, GONE and INVISIBLE are 0, 4 and 8 respectively), so we use this converter.
 *
 * There is no need to specify that this converter should be used. [BindingConversion]s are
 * applied automatically.
 */
object CustomBindingConverters {

    @BindingConversion
    @JvmStatic
    fun booleanToVisibility(isVisible: Boolean): Int {
        return if (isVisible) View.VISIBLE else View.GONE
    }
}