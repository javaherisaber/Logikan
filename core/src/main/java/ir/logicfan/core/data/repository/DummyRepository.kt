package ir.logicfan.core.data.repository

import io.reactivex.Observable
import ir.logicfan.core.data.network.entity.DummyData

interface DummyRepository {
    fun getItem(): Observable<DummyData>
    fun getList(): Observable<List<DummyData>>
}