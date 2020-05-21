package ir.logicfan.core.data.repository

import ir.logicfan.core.data.base.DataReactiveUtil
import ir.logicfan.core.data.entity.DummyData
import ir.logicfan.core.data.network.endpoint.DummyApi
import ir.logicfan.core.domain.repository.DummyRepository
import javax.inject.Inject

class DummyRepositoryImpl @Inject constructor(
    private val dummyApi: DummyApi,
    private val listReactiveUtil: DataReactiveUtil<List<DummyData>>,
    private val singleReactiveUtil: DataReactiveUtil<DummyData>
) : DummyRepository {

    override fun getItem() = singleReactiveUtil.composeWith {
        dummyApi.getItem().map { singleReactiveUtil.map(it) }
    }

    override fun getList() = listReactiveUtil.composeWith {
        dummyApi.getList().map { listReactiveUtil.map(it) }
    }
}