package ir.logicbase.core.di.module

import dagger.Module
import dagger.Provides
import dagger.Reusable
import ir.logicbase.core.data.network.endpoint.DummyApi
import ir.logicbase.core.data.network.endpoint.GenericApi
import retrofit2.Retrofit

@Module
abstract class BaseEndpointModule {

    companion object {
        @Provides
        @Reusable
        fun dummyApi(retrofit: Retrofit): DummyApi = retrofit.create(DummyApi::class.java)

        @Provides
        @Reusable
        fun genericApi(retrofit: Retrofit): GenericApi = retrofit.create(GenericApi::class.java)
    }
}