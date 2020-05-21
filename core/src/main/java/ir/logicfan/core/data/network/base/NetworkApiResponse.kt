package ir.logicfan.core.data.network.base

import com.google.gson.annotations.SerializedName
import io.reactivex.Observable
import ir.logicfan.core.data.entity.ErrorData
import ir.logicfan.core.data.entity.PaginationData

typealias NetworkResult<S> = Observable<NetworkApiResponse<S>>
typealias ImperativeNetworkResult = NetworkResult<Nothing>
/**
 * Base wrapper of all network api responses
 */
data class NetworkApiResponse<out T>(
    @field:SerializedName("success")
    val success: Boolean,
    @field:SerializedName("data")
    val data: T?,
    @field:SerializedName("pagination")
    val pagination: PaginationData?,
    @field:SerializedName("error")
    val error: List<ErrorData>?
)