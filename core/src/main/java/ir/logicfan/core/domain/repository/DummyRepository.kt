/*
 * Copyright (C) Logicfan, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential
 * Written by Mahdi Javaheri Saber <m.javaherisaber@gmail.com>, Mohammad Hosein Abedini <mohammadhoseinabedini@gmail.com>  2020.
 */

package ir.logicfan.core.domain.repository

import io.reactivex.Observable
import ir.logicfan.core.data.base.DataOutcome
import ir.logicfan.core.data.entity.DummyData

interface DummyRepository {
    fun getItem(): Observable<DataOutcome<DummyData>>
    fun getList(): Observable<DataOutcome<List<DummyData>>>
}