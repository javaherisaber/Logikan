package ir.logicbase.core.data.network.base

import com.google.gson.annotations.SerializedName
import io.reactivex.Observable
import ir.logicbase.core.data.entity.EmptyListData
import ir.logicbase.core.data.entity.PaginationData
import ir.logicbase.core.data.entity.UpdateData

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