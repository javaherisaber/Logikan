package ir.logicbase.logikan.data.network.base

import com.google.gson.annotations.SerializedName
import ir.logicbase.logikan.data.entity.ErrorData
import ir.logicbase.logikan.data.entity.UpdateData

data class NetworkApiError(
    @field:SerializedName("success")
    val success: Boolean,
    @field:SerializedName("error")
    val error: List<ErrorData>?,
    @field:SerializedName("update")
    val update: UpdateData?
)