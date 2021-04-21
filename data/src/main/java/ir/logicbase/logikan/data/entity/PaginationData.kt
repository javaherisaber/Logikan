package ir.logicbase.logikan.data.entity

import com.google.gson.annotations.SerializedName

data class PaginationData(
    @field:SerializedName("has_more_page")
    val hasMorePage: Boolean,
    @field:SerializedName("total")
    val total: Int
)