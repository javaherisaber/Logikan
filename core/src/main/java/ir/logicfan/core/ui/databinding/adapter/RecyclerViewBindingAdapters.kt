/*
 * Copyright (C) Logicfan, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential
 * Written by Mahdi Javaheri Saber <m.javaherisaber@gmail.com>, Mohammad Hosein Abedini <mohammadhoseinabedini@gmail.com>  2020.
 */

package ir.logicfan.core.ui.databinding.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.logicfan.core.R
import ir.logicfan.core.ui.recyclerview.decorator.GridMarginItemDecoration
import ir.logicfan.core.ui.recyclerview.decorator.HorizontalMarginItemDecoration
import ir.logicfan.core.ui.recyclerview.decorator.VerticalMarginItemDecoration

/**
 * Add a default gray color divider to your recyclerView
 *
 * @param hasItemDecoration if true itemDecoration will be added
 * @param dividerOrientation if provided it decide orientation, if null Vertical will be used
 */
@BindingAdapter(
    value = ["hasDefaultDividerItemDecoration", "dividerOrientation"],
    requireAll = false
)
fun RecyclerView.addDefaultDividerItemDecoration(
    hasItemDecoration: Boolean,
    dividerOrientation: Int?
) {
    if (hasItemDecoration) {
        if (dividerOrientation == null) {
            // no orientation provided, use VERTICAL by default
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        } else {
            // orientation provided
            addItemDecoration(DividerItemDecoration(context, dividerOrientation))
        }
    }
}

/**
 * Add margin to recyclerView items
 *
 * @param hasItemDecoration if true decorator will be added
 * @param margin applies to all sides of your item
 */
@BindingAdapter(
    value = ["hasMarginItemDecoration", "itemDecorationMargin"],
    requireAll = false
)
fun RecyclerView.addGridMarginItemDecorator(
    hasItemDecoration: Boolean,
    margin: Int?
) {
    if (hasItemDecoration) {
        var itemMargin = resources.getDimension(R.dimen.core_margin_low).toInt()
        margin?.let {
            itemMargin = it
        }
        when (val itemLayoutManager = layoutManager) {
            is GridLayoutManager -> addItemDecoration(GridMarginItemDecoration(itemMargin))
            is LinearLayoutManager -> when (itemLayoutManager.orientation) {
                LinearLayoutManager.HORIZONTAL -> addItemDecoration(
                    HorizontalMarginItemDecoration(itemMargin)
                )
                LinearLayoutManager.VERTICAL -> addItemDecoration(
                    VerticalMarginItemDecoration(itemMargin)
                )
            }
        }
    }
}