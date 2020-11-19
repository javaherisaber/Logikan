package ir.logicbase.core.ui.feature.dummy

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ir.logicbase.core.di.key.ViewModelKey
import ir.logicbase.core.di.scope.PerFragment

@Module
abstract class DummyModule {
    @Binds
    @PerFragment
    @IntoMap
    @ViewModelKey(DummyViewModel::class)
    abstract fun bindDummyViewModel(dummyViewModel: DummyViewModel): ViewModel
}