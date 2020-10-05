package ir.logicfan.core.data.network.base

import com.google.gson.annotations.SerializedName
import io.reactivex.Observable
import ir.logicfan.core.data.entity.EmptyListData
import ir.logicfan.core.data.entity.ErrorData
import ir.logicfan.core.data.entity.PaginationData
import ir.logicfan.core.data.entity.UpdateData

typealias NetworkResult<S> = Observable<NetworkApiSuccess<S>>
typealias ImperativeNetworkResult = NetworkResult<Nothing>
/**
 * Base wrapper of all network api responses
 */
data class NetworkApiSuccess<out T>(
    @field:SerializedName("success")
    val success: Boolean,
    @field:SerializedName("data")
    val data: T?,
    @field:SerializedName("pagination")
    val pagination: PaginationData?,
    @field:SerializedName("empty_list")
    val emptyList: EmptyListData?,
    @field:SerializedName("update")
    val update: UpdateData?
)