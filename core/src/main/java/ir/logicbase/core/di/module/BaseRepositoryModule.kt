package ir.logicbase.core.di.module

import dagger.Module
import dagger.Provides
import dagger.Reusable
import ir.logicbase.core.data.util.ReactiveUtil
import ir.logicbase.core.data.network.endpoint.DummyApi
import ir.logicbase.core.data.network.endpoint.GenericApi
import ir.logicbase.core.data.reactive.ASyncTransformer
import ir.logicbase.core.domain.repository.DummyRepository
import ir.logicbase.core.data.repository.DummyRepositoryImpl
import ir.logicbase.core.domain.repository.GenericRepository
import ir.logicbase.core.data.repository.GenericRepositoryImpl

@Module
abstract class BaseRepositoryModule {

    companion object {
        @Provides
        @Reusable
        fun dummyRepository(dummyApi: DummyApi): DummyRepository = DummyRepositoryImpl(
            dummyApi,
            ReactiveUtil(),
            ReactiveUtil()
        )

        @Provides
        @Reusable
        fun genericRepository(genericApi: GenericApi): GenericRepository = GenericRepositoryImpl(
            genericApi, ASyncTransformer()
        )
    }
}