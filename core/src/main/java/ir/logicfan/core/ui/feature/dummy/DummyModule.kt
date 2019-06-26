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
    abstract fun dummyViewModel(dummyViewModel: DummyViewModel): ViewModel
}