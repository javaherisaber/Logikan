package ir.logicfan.core.app.ui.base

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import ir.logicfan.core.app.di.component.DaggerAppComponent

class BaseApplication: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out dagger.android.support.DaggerApplication> {
        val appComponent = DaggerAppComponent.builder().application(this).build()
        appComponent.inject(this)
        return appComponent
    }

}