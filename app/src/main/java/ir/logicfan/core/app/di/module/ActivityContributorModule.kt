package ir.logicfan.core.app.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ir.logicfan.core.app.ui.main.MainActivity
import ir.logicfan.core.app.ui.main.MainActivityModule
import ir.logicfan.core.di.scope.PerActivity

@Module
abstract class ActivityContributorModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun mainActivity(): MainActivity
}