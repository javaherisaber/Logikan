/*
 * Copyright (C) Logicfan, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential
 * Written by Mahdi Javaheri Saber <m.javaherisaber@gmail.com>, Mohammad Hosein Abedini <mohammadhoseinabedini@gmail.com>  2020.
 */

package ir.logicfan.core.data.network.base

import com.google.gson.annotations.SerializedName
import ir.logicfan.core.data.entity.ErrorData
import ir.logicfan.core.data.entity.PaginationData

/**
 * Base wrapper of all network api responses
 */
data class NetworkApiResponse<out T>(
    @field:SerializedName("success")
    val success: Boolean,
    @field:SerializedName("data")
    val data: T?,
    @field:SerializedName("pagination")
    val pagination: PaginationData?,
    @field:SerializedName("error")
    val error: List<ErrorData>?
)