package ir.logicfan.core.data.entity

import com.google.gson.annotations.SerializedName

data class EmptyListData(
    @field:SerializedName("image")
    val image: String,
    @field:SerializedName("message")
    val message: String
)