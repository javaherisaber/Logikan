package ir.logicfan.core.data.repository

import io.reactivex.Observable
import ir.logicfan.core.data.base.DataOutcome
import ir.logicfan.core.data.entity.DummyData

interface DummyRepository {
    fun getItem(): Observable<DataOutcome<DummyData>>
    fun getList(): Observable<DataOutcome<List<DummyData>>>
}