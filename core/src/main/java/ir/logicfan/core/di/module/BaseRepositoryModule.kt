package ir.logicfan.core.di.module

import dagger.Module
import dagger.Provides
import dagger.Reusable
import ir.logicfan.core.data.base.DataReactiveUtil
import ir.logicfan.core.data.network.endpoint.DummyApi
import ir.logicfan.core.data.network.endpoint.GenericApi
import ir.logicfan.core.data.mapper.NetworkApiResponseToDataOutcomeMapper
import ir.logicfan.core.data.reactive.ASyncTransformer
import ir.logicfan.core.domain.repository.DummyRepository
import ir.logicfan.core.data.repository.DummyRepositoryImpl
import ir.logicfan.core.domain.repository.GenericRepository
import ir.logicfan.core.data.repository.GenericRepositoryImpl

@Module
abstract class BaseRepositoryModule {

    @Module
    companion object {
        @Provides
        @Reusable
        @JvmStatic
        fun dummyRepository(dummyApi: DummyApi): DummyRepository = DummyRepositoryImpl(
            dummyApi,
            DataReactiveUtil(),
            DataReactiveUtil()
        )

        @Provides
        @Reusable
        @JvmStatic
        fun genericRepository(genericApi: GenericApi): GenericRepository = GenericRepositoryImpl(
            genericApi, ASyncTransformer()
        )
    }
}