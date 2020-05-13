package ir.logicfan.core.di.module

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.disposables.CompositeDisposable
import ir.logicfan.core.di.base.ViewModelFactory

@Module
abstract class BaseModule {

    @Binds
    @Reusable
    abstract fun context(application: Application): Context

    @Binds
    abstract fun viewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun compositeDisposable(): CompositeDisposable = CompositeDisposable()
    }
}
