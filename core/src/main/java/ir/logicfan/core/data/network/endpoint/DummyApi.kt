package ir.logicfan.core.data.network.endpoint

import io.reactivex.Observable
import ir.logicfan.core.data.network.base.NetworkApiResponse
import ir.logicfan.core.data.network.entity.DummyRemoteData
import retrofit2.http.GET

/**
 * Use to implement and Spike on idea's faster
 */
interface DummyApi {

    @GET("dummy/item")
    fun getItem(): Observable<NetworkApiResponse<DummyRemoteData>>

    @GET("dummy/list")
    fun getList(): Observable<NetworkApiResponse<List<DummyRemoteData>>>
}