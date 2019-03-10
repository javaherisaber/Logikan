package ir.logicfan.core.ui.base

import android.app.Activity
import android.content.Context
import dagger.Binds
import dagger.Module
import ir.logicfan.core.di.qulifier.ActivityContext
import ir.logicfan.core.di.scope.PerActivity

@Module
abstract class BaseActivityModule {

    @Binds
    @ActivityContext
    @PerActivity
    abstract fun activityContext(activity: Activity): Context
}
