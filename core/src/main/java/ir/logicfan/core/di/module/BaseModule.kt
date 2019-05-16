package ir.logicfan.core.di.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import ir.logicfan.core.data.preferences.BasePreferences
import ir.logicfan.core.di.qulifier.ApplicationContext

@Module
abstract class BaseModule {

    @Binds
    @ApplicationContext
    abstract fun context(application: Application): Context

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun disposable(): CompositeDisposable {
            return CompositeDisposable()
        }

        @Provides
        @JvmStatic
        fun basePreference(@ApplicationContext context: Context, secret: CharArray): BasePreferences {
            return BasePreferences(context, secret)
        }
    }
}
