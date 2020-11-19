package ir.logicbase.core.domain.repository

import ir.logicbase.core.data.base.DataResult
import ir.logicbase.core.data.entity.DummyData

interface DummyRepository {
    fun getItem(): DataResult<DummyData>
    fun getList(): DataResult<List<DummyData>>
}