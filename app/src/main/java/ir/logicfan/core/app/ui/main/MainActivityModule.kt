package ir.logicfan.core.app.ui.main

import androidx.appcompat.app.AppCompatActivity
import dagger.Binds
import dagger.Module
import ir.logicfan.core.di.scope.PerActivity
import ir.logicfan.core.ui.base.BaseActivityModule

@Module(includes = [BaseActivityModule::class])
abstract class MainActivityModule {
    @Binds
    @PerActivity
    abstract fun appCompatActivity(mainActivity: MainActivity): AppCompatActivity
}