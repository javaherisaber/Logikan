package ir.logicbase.core.data.network.endpoint

import ir.logicbase.core.data.entity.DummyData
import ir.logicbase.core.data.network.base.NetworkResult
import retrofit2.http.GET

/**
 * Use to implement and Spike on idea's faster
 */
interface DummyApi {

    @GET("dummy/item")
    fun getItem(): NetworkResult<DummyData>

    @GET("dummy/list")
    fun getList(): NetworkResult<List<DummyData>>
}