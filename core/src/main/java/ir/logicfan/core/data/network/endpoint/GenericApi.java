package ir.logicfan.core.data.network.endpoint;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * This endpoint will be used to handle requests without base url
 */
public interface GenericApi {

    @GET
    Single<ResponseBody> download(@Url String url);  // base url will be overridden
}
