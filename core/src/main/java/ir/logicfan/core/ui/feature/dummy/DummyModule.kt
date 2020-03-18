/*
 * Copyright (C) Logicfan, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential
 * Written by Mahdi Javaheri Saber <m.javaherisaber@gmail.com>, Mohammad Hosein Abedini <mohammadhoseinabedini@gmail.com>  2020.
 */

package ir.logicfan.core.ui.feature.dummy

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ir.logicfan.core.di.key.ViewModelKey
import ir.logicfan.core.di.scope.PerFragment

@Module
abstract class DummyModule {
    @Binds
    @PerFragment
    @IntoMap
    @ViewModelKey(DummyViewModel::class)
    abstract fun bindDummyViewModel(dummyViewModel: DummyViewModel): ViewModel
}