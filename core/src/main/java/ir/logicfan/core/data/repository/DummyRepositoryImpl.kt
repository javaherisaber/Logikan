package ir.logicfan.core.data.repository

import io.reactivex.Observable
import ir.logicfan.core.data.base.DataOutcome
import ir.logicfan.core.data.base.Mapper
import ir.logicfan.core.data.entity.DummyData
import ir.logicfan.core.data.network.base.NetworkApiResponse
import ir.logicfan.core.data.network.endpoint.DummyApi
import ir.logicfan.core.data.reactive.Transformer
import ir.logicfan.core.data.util.ReactiveUtils.composeWith
import javax.inject.Inject

class DummyRepositoryImpl @Inject
constructor(
    private val dummyApi: DummyApi,
    private val dataResponseOutcomeMapper: Mapper<NetworkApiResponse<DummyData>, DataOutcome<DummyData>>,
    private val dataResponseOutcomeMapperList: Mapper<NetworkApiResponse<List<DummyData>>, DataOutcome<List<DummyData>>>,
    private val itemTransformer: Transformer<DataOutcome<DummyData>>,
    private val listTransformer: Transformer<DataOutcome<List<DummyData>>>
) : DummyRepository {

    override fun getItem(): Observable<DataOutcome<DummyData>> = composeWith(itemTransformer) {
        dummyApi.getItem().map { dataResponseOutcomeMapper.mapFrom(it) }
    }

    override fun getList(): Observable<DataOutcome<List<DummyData>>> = composeWith(listTransformer) {
        dummyApi.getList().map { dataResponseOutcomeMapperList.mapFrom(it) }
    }
}