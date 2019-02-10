package ir.logicfan.core.data.network.error;

import android.content.Context;
import ir.logicfan.core.util.Connectivity;
import okhttp3.Interceptor;
import okhttp3.Response;

import javax.inject.Inject;
import java.io.IOException;

public class ErrorHandlingInterceptor implements Interceptor {
    private Context context;

    @Inject
    public ErrorHandlingInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (!Connectivity.isUserOnline(context))
            throw new UserOfflineException();
        return chain.proceed(chain.request());
    }
}
