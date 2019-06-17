package ir.logicfan.core.ui.base

import android.os.Bundle
import android.widget.Toast
import dagger.android.support.DaggerAppCompatActivity
import ir.logicfan.core.data.error.DataException
import ir.logicfan.core.data.preferences.BaseSharedPreferences
import ir.logicfan.core.ui.listener.DataTerminalErrorListener
import ir.logicfan.core.ui.util.LocaleUtils
import javax.inject.Inject

/**
 * Created by Mahdi on 12/16/2017.
 * All of our activities extends this class to inherit top level functionality
 */

abstract class BaseActivity : DaggerAppCompatActivity(), DataTerminalErrorListener {

    @Inject
    lateinit var baseSharedPreferences: BaseSharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocaleUtils.setLocale(this, baseSharedPreferences.localeSetting)
    }

    override fun onDataTerminalError(throwable: Throwable) {
        when (throwable) {
            is DataException.Terminal.UnAuthorized -> {
                // logout user info in ActivityBaseViewModel (new class maybe extending from BaseViewModel)
                // and add login fragment here (also add to back stack)
                Toast.makeText(this, "please login again", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
