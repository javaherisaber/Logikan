package ir.logicfan.core.ui.base

import android.os.Bundle
import android.view.MenuItem
import dagger.android.support.DaggerAppCompatActivity
import ir.logicfan.core.data.preferences.BaseSharedPreferences
import ir.logicfan.core.util.LocaleUtils
import javax.inject.Inject

/**
 * Created by Mahdi on 12/16/2017.
 * All of our activities extends this class to inherit top level functionality
 */

abstract class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var baseSharedPreferences: BaseSharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocaleUtils.setLocale(this, baseSharedPreferences.localeSetting)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}
