package ir.logicbase.core.data.network.base

import com.google.gson.annotations.SerializedName
import ir.logicbase.core.data.entity.ErrorData

data class NetworkApiError(
    @field:SerializedName("success")
    val success: Boolean,
    @field:SerializedName("error")
    val error: List<ErrorData>?
)