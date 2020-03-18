/*
 * Copyright (C) Logicfan, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential
 * Written by Mahdi Javaheri Saber <m.javaherisaber@gmail.com>, Mohammad Hosein Abedini <mohammadhoseinabedini@gmail.com>  2020.
 */

package ir.logicfan.core.di.module

import dagger.Module
import dagger.Provides
import dagger.Reusable
import ir.logicfan.core.data.network.endpoint.DummyApi
import ir.logicfan.core.data.network.endpoint.GenericApi
import retrofit2.Retrofit

@Module
abstract class BaseEndpointModule {

    @Module
    companion object {
        @Provides
        @Reusable
        @JvmStatic
        fun dummyApi(retrofit: Retrofit): DummyApi = retrofit.create(DummyApi::class.java)

        @Provides
        @Reusable
        @JvmStatic
        fun genericApi(retrofit: Retrofit): GenericApi = retrofit.create(GenericApi::class.java)
    }
}