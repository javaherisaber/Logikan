package ir.logicfan.core.data.entity

import com.google.gson.annotations.SerializedName

data class PaginationData(
    @field:SerializedName("current_page")
    val currentPage: Int,
    @field:SerializedName("has_more_page")
    val hasMorePage: Boolean,
    @field:SerializedName("total_entries")
    val totalEntries: Int,
    @field:SerializedName("per_page")
    val perPage: Int,
    @field:SerializedName("total")
    val total: Int
)