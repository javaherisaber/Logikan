package ir.logicfan.core.di.module

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.Reusable
import ir.logicfan.core.ui.base.BaseViewModelFactory

@Module
abstract class BaseModule {

    @Binds
    @Reusable
    abstract fun context(application: Application): Context

    @Binds
    @Reusable
    abstract fun baseViewModelFactory(viewModelFactory: BaseViewModelFactory): ViewModelProvider.Factory
}
