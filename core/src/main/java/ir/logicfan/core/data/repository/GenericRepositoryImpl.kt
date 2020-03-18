/*
 * Copyright (C) Logicfan, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential
 * Written by Mahdi Javaheri Saber <m.javaherisaber@gmail.com>, Mohammad Hosein Abedini <mohammadhoseinabedini@gmail.com>  2020.
 */

package ir.logicfan.core.data.repository

import io.reactivex.Observable
import ir.logicfan.core.data.network.endpoint.GenericApi
import ir.logicfan.core.data.reactive.Transformer
import ir.logicfan.core.data.util.ReactiveUtils.composeWith
import ir.logicfan.core.domain.repository.GenericRepository
import okhttp3.ResponseBody
import javax.inject.Inject

class GenericRepositoryImpl @Inject
constructor(
    private val genericApi: GenericApi,
    private val transformer: Transformer<ResponseBody>
) : GenericRepository {

    override fun download(url: String): Observable<ResponseBody> = composeWith(transformer) {
        genericApi.download(url)
    }
}