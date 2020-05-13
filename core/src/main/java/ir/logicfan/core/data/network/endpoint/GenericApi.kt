package ir.logicfan.core.data.network.endpoint

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * This endpoint will be used to handle requests without base url
 */
interface GenericApi {

    @GET
    fun download(@Url url: String): Observable<ResponseBody>   // base url will be overridden
}
