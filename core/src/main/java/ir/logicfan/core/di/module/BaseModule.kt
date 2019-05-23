package ir.logicfan.core.di.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import ir.logicfan.core.data.preferences.BaseSharedPreferences
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
        fun baseSharedPreferences(@ApplicationContext context: Context, secret: CharArray): BaseSharedPreferences =
            BaseSharedPreferences(context, secret)
    }
}
