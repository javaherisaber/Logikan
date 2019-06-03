package ir.logicfan.core.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ir.logicfan.core.di.key.ViewModelKey
import ir.logicfan.core.di.scope.PerFragment
import ir.logicfan.core.ui.viewmodel.DummyViewModel

@Module
abstract class DummyFragmentModule {
    @Binds
    @PerFragment
    @IntoMap
    @ViewModelKey(DummyViewModel::class)
    abstract fun dummyViewModel(dummyViewModel: DummyViewModel): ViewModel
}