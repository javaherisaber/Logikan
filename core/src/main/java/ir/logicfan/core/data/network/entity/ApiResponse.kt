package ir.logicfan.core.data.network.entity

import com.google.gson.annotations.SerializedName

data class ApiResponse<out T>(
    @field:SerializedName("success")
    val success: Boolean,
    @field:SerializedName("data")
    val data: T?,
    @field:SerializedName("pagination")
    val pagination: Pagination?,
    @field:SerializedName("error")
    val error: List<Error>?
)

data class Error(
    @field:SerializedName("code")
    val code: Int,
    @field:SerializedName("msg")
    val msg: String
)

data class Pagination(
    @field:SerializedName("current_page")
    val currentPage: Int,
    @field:SerializedName("more_page")
    val morePage: Boolean,
    @field:SerializedName("total_entries")
    val totalEntries: Int,
    @field:SerializedName("from")
    val from: Int,
    @field:SerializedName("to")
    val to: Int,
    @field:SerializedName("per_page")
    val perPage: Int
)