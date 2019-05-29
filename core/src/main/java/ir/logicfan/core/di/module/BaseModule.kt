package ir.logicfan.core.di.module

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import ir.logicfan.core.data.preferences.BaseSharedPreferences
import ir.logicfan.core.di.qulifier.ApplicationContext
import ir.logicfan.core.ui.base.BaseViewModelFactory

@Module
abstract class BaseModule {

    @Binds
    @ApplicationContext
    abstract fun context(application: Application): Context

    @Binds
    abstract fun baseViewModelFactory(viewModelFactory: BaseViewModelFactory): ViewModelProvider.Factory

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun baseSharedPreferences(@ApplicationContext context: Context, secret: CharArray): BaseSharedPreferences =
            BaseSharedPreferences(context, secret)
    }
}
