package ir.logicfan.core.data.repository

import io.reactivex.Observable
import ir.logicfan.core.data.base.DataOutcome
import ir.logicfan.core.data.base.Mapper
import ir.logicfan.core.data.network.base.NetworkApiResponse
import ir.logicfan.core.data.network.endpoint.DummyApi
import ir.logicfan.core.data.network.entity.DummyRemoteData
import ir.logicfan.core.data.reactive.Transformer
import ir.logicfan.core.data.util.ReactiveUtils.composeWith
import javax.inject.Inject

class DummyRepositoryImpl @Inject
constructor(
    private val dummyApi: DummyApi,
    private val dataResponseOutcomeMapper: Mapper<NetworkApiResponse<DummyRemoteData>, DataOutcome<DummyRemoteData>>,
    private val dataResponseOutcomeMapperList: Mapper<NetworkApiResponse<List<DummyRemoteData>>, DataOutcome<List<DummyRemoteData>>>,
    private val itemTransformer: Transformer<DataOutcome<DummyRemoteData>>,
    private val listTransformer: Transformer<DataOutcome<List<DummyRemoteData>>>
) : DummyRepository {

    override fun getItem(): Observable<DataOutcome<DummyRemoteData>> = composeWith(itemTransformer) {
        dummyApi.getItem().map { dataResponseOutcomeMapper.mapFrom(it) }
    }

    override fun getList(): Observable<DataOutcome<List<DummyRemoteData>>> = composeWith(listTransformer) {
        dummyApi.getList().map { dataResponseOutcomeMapperList.mapFrom(it) }
    }
}