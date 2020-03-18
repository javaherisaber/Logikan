/*
 * Copyright (C) Logicfan, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential
 * Written by Mahdi Javaheri Saber <m.javaherisaber@gmail.com>, Mohammad Hosein Abedini <mohammadhoseinabedini@gmail.com>  2020.
 */

package ir.logicfan.core.ui.recyclerview.decorator

import android.graphics.Rect
import android.view.View
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Add margin to Grid RecyclerView items
 */
class GridMarginItemDecoration(private val margin: Int) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            val spanCount = (parent.layoutManager as GridLayoutManager).spanCount
            val position = parent.getChildAdapterPosition(view)
            if (position in 0 until spanCount) {
                // only first row have top margin
                top = margin
            }
            val isLayoutRtl = parent.layoutDirection == ViewCompat.LAYOUT_DIRECTION_RTL
            val column = position.rem(spanCount)
            val firstColumn = 0
            val lastColumn = spanCount - 1
            when (column) {
                firstColumn -> {
                    if (isLayoutRtl) {
                        right = margin
                        left = margin / 2
                    } else {
                        left = margin
                        right = margin / 2
                    }
                }
                lastColumn -> {
                    if (isLayoutRtl) {
                        left = margin
                        right = margin / 2
                    } else {
                        right = margin
                        left = margin / 2
                    }
                }
                in (firstColumn + 1) until lastColumn -> {
                    left = margin / 2
                    right = margin / 2
                }
            }
            bottom = margin
        }
    }
}