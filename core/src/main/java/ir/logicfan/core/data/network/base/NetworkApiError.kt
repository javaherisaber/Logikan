package ir.logicfan.core.data.network.base

import com.google.gson.annotations.SerializedName
import ir.logicfan.core.data.entity.ErrorData

data class NetworkApiError(
    @field:SerializedName("success")
    val success: Boolean,
    @field:SerializedName("error")
    val error: List<ErrorData>
)