/*
 * Copyright (C) Logicfan, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential
 * Written by Mahdi Javaheri Saber <m.javaherisaber@gmail.com>, Mohammad Hosein Abedini <mohammadhoseinabedini@gmail.com>  2020.
 */

package ir.logicfan.core.data.network.endpoint

import io.reactivex.Observable
import ir.logicfan.core.data.network.base.NetworkApiResponse
import ir.logicfan.core.data.entity.DummyData
import retrofit2.http.GET

/**
 * Use to implement and Spike on idea's faster
 */
interface DummyApi {

    @GET("dummy/item")
    fun getItem(): Observable<NetworkApiResponse<DummyData>>

    @GET("dummy/list")
    fun getList(): Observable<NetworkApiResponse<List<DummyData>>>
}