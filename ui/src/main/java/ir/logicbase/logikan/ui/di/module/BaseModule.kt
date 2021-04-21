package ir.logicbase.logikan.ui.di.module

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.disposables.CompositeDisposable
import ir.logicbase.logikan.ui.di.base.ViewModelFactory

@Module
abstract class BaseModule {

    @Binds
    @Reusable
    abstract fun context(application: Application): Context

    @Binds
    abstract fun viewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    companion object {
        @Provides
        fun compositeDisposable(): CompositeDisposable = CompositeDisposable()
    }
}
