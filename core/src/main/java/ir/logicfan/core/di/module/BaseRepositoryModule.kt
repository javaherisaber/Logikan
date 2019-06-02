package ir.logicfan.core.di.module

import dagger.Module
import dagger.Provides
import dagger.Reusable
import ir.logicfan.core.data.network.endpoint.DummyApi
import ir.logicfan.core.data.network.mapper.NetworkApiResponseOutcomeMapper
import ir.logicfan.core.data.reactive.ASyncTransformer
import ir.logicfan.core.data.repository.DummyRepository
import ir.logicfan.core.data.repository.DummyRepositoryImpl

@Module
abstract class BaseRepositoryModule {

    @Module
    companion object {
        @Provides
        @Reusable
        @JvmStatic
        fun dummyRepository(dummyApi: DummyApi): DummyRepository = DummyRepositoryImpl(
            dummyApi,
            NetworkApiResponseOutcomeMapper(),
            NetworkApiResponseOutcomeMapper(),
            ASyncTransformer(),
            ASyncTransformer()
        )
    }
}