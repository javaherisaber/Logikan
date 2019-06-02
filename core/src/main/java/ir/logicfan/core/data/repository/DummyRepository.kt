package ir.logicfan.core.data.repository

import io.reactivex.Observable
import ir.logicfan.core.data.base.DataOutcome
import ir.logicfan.core.data.network.entity.DummyRemoteData

interface DummyRepository {
    fun getItem(): Observable<DataOutcome<DummyRemoteData>>
    fun getList(): Observable<DataOutcome<List<DummyRemoteData>>>
}