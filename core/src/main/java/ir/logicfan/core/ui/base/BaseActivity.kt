package ir.logicfan.core.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import ir.logicfan.core.data.sharedpreferences.BaseSharedPreferences
import ir.logicfan.core.ui.util.LocaleUtils
import javax.inject.Inject

/**
 * Created by Mahdi on 12/16/2017.
 * All of our activities extends this class to inherit top level functionality
 */

abstract class BaseActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>
    @Inject
    lateinit var baseSharedPreferences: BaseSharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        LocaleUtils.setLocale(this, baseSharedPreferences.localeSetting)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}
