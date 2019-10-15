package ir.logicfan.core.data.entity

import com.google.gson.annotations.SerializedName

data class ErrorData(
    @field:SerializedName("code")
    val code: Int,
    @field:SerializedName("message")
    val msg: String
)