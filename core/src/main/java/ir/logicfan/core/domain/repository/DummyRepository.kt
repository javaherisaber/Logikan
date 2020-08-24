package ir.logicfan.core.domain.repository

import ir.logicfan.core.data.base.DataResult
import ir.logicfan.core.data.entity.DummyData

interface DummyRepository {
    fun getItem(): DataResult<DummyData>
    fun getList(): DataResult<List<DummyData>>
}