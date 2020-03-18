/*
 * Copyright (C) Logicfan, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential
 * Written by Mahdi Javaheri Saber <m.javaherisaber@gmail.com>, Mohammad Hosein Abedini <mohammadhoseinabedini@gmail.com>  2020.
 */

package ir.logicfan.core.ui.feature.dummy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import ir.logicfan.core.ui.base.BaseViewModel
import javax.inject.Inject

class DummyViewModel @Inject constructor(compositeDisposable: CompositeDisposable) : BaseViewModel(compositeDisposable) {

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    init {
        this._title.value = "Dummy data to showcase your idea"
    }
}