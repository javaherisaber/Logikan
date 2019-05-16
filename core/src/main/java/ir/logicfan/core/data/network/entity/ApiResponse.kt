package ir.logicfan.core.data.network.entity

import com.google.gson.annotations.SerializedName

data class ApiResponse<out T>(@field:SerializedName("status")
                              val status: List<Status>,
                              @field:SerializedName("data")
                              val data: T)

data class Status(@field:SerializedName("code")
                  val code: Int,
                  @field:SerializedName("msg")
                  val msg: String)