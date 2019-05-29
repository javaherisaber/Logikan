package ir.logicfan.core.data.repository

import io.reactivex.Observable
import ir.logicfan.core.data.network.endpoint.DummyApi
import ir.logicfan.core.data.network.entity.DummyData
import ir.logicfan.core.data.util.ReactiveUtils.composeWith
import ir.logicfan.core.data.reactive.Transformer
import javax.inject.Inject

class DummyRepositoryImpl @Inject
constructor(
    private val dummyApi: DummyApi,
    private val itemTransformer: Transformer<DummyData>,
    private val listTransformer: Transformer<List<DummyData>>
) : DummyRepository {

    override fun getItem(): Observable<DummyData> = composeWith(itemTransformer) {
        dummyApi.getItem()
    }

    override fun getList(): Observable<List<DummyData>> = composeWith(listTransformer) {
        dummyApi.getList()
    }
}