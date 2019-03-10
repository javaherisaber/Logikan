package ir.logicfan.core.di.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import ir.logicfan.core.data.pref.BasePrefManager
import ir.logicfan.core.di.qulifier.ApplicationContext

@Module
abstract class BaseModule {

    @Binds
    @ApplicationContext
    abstract fun context(application: Application): Context

    companion object {

        @Provides
        fun disposable(): CompositeDisposable {
            return CompositeDisposable()
        }

        @Provides
        fun basePrefManager(@ApplicationContext context: Context): BasePrefManager {
            return BasePrefManager(context)
        }
    }
}
