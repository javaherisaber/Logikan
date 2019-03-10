package ir.logicfan.core.ui.base

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import ir.logicfan.core.di.qulifier.ActivityContext
import ir.logicfan.core.di.qulifier.ActivityFragmentManager
import ir.logicfan.core.di.scope.PerActivity

@Module
abstract class BaseActivityModule {

    @Binds
    @ActivityContext
    @PerActivity
    abstract fun activityContext(activity: Activity): Context

    companion object {

        @Provides
        @ActivityFragmentManager
        @PerActivity
        fun activityFragmentManager(activity: AppCompatActivity): FragmentManager {
            return activity.supportFragmentManager
        }
    }
}
