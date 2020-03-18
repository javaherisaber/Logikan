/*
 * Copyright (C) Logicfan, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential
 * Written by Mahdi Javaheri Saber <m.javaherisaber@gmail.com>, Mohammad Hosein Abedini <mohammadhoseinabedini@gmail.com>  2020.
 */

package ir.logicfan.core.data.entity

import com.google.gson.annotations.SerializedName

data class PaginationData(
    @field:SerializedName("has_more_page")
    val hasMorePage: Boolean,
    @field:SerializedName("total")
    val total: Int
)