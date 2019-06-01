package ir.logicfan.core.di.module

import dagger.Module
import dagger.Provides
import dagger.Reusable
import ir.logicfan.core.data.network.endpoint.DummyApi
import retrofit2.Retrofit

@Module
abstract class BaseEndpointModule {

    @Module
    companion object {
        @Provides
        @Reusable
        @JvmStatic
        fun dummyApi(retrofit: Retrofit): DummyApi = retrofit.create(DummyApi::class.java)
    }
}