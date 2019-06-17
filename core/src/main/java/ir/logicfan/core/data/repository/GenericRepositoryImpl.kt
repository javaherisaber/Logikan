package ir.logicfan.core.data.repository

import io.reactivex.Observable
import ir.logicfan.core.data.network.endpoint.GenericApi
import ir.logicfan.core.data.reactive.Transformer
import ir.logicfan.core.data.util.ReactiveUtils.composeWith
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