/*
 * Copyright (C) Logicfan, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential
 * Written by Mahdi Javaheri Saber <m.javaherisaber@gmail.com>, Mohammad Hosein Abedini <mohammadhoseinabedini@gmail.com>  2020.
 */

package ir.logicfan.core.ui.util.extension

import androidx.lifecycle.MutableLiveData
import ir.logicfan.core.ui.reactive.OneShotEvent

/**
 * Post a OneShotEvent of type Unit
 */
fun MutableLiveData<OneShotEvent<Unit>>.postOneShotUnit() {
    this.value = OneShotEvent(Unit)
}

/**
 * Post a OneShotEvent of type T
 */
fun <T> MutableLiveData<OneShotEvent<T>>.postOneShot(value: T){
    this.value = OneShotEvent(value)
}