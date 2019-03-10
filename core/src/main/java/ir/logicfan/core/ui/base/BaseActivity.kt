package ir.logicfan.core.ui.base

import android.app.Activity
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import butterknife.ButterKnife
import butterknife.Unbinder
import dagger.android.support.DaggerAppCompatActivity
import ir.logicfan.core.data.pref.BasePrefManager
import ir.logicfan.core.di.qulifier.ActivityFragmentManager
import ir.logicfan.core.util.LocaleManager

import javax.inject.Inject

/**
 * Created by Mahdi on 12/16/2017.
 * All of our activities extends this class to inherit top level functionality
 */

abstract class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var prefManager: BasePrefManager

    @Inject
    @ActivityFragmentManager
    lateinit var fragmentManager: FragmentManager

    private var butterKnifeUnbinder: Unbinder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocaleManager.setLocale(this, prefManager.settingsPrefLangList)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    protected fun replaceFragmentWithFadeAnimation(@IdRes container: Int, fragment: Fragment, addToBackStack: Boolean) {
        val ft = fragmentManager.beginTransaction()
        ft.replace(container, fragment)
        if (addToBackStack) {
            ft.addToBackStack(null) // argument name is optional
        }
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)  // add animation to replacement process
        ft.commit()
    }

    protected fun setButterKnifeUnbinder(activity: Activity) {
        butterKnifeUnbinder = ButterKnife.bind(activity)
    }

    override fun onDestroy() {
        butterKnifeUnbinder?.unbind()
        super.onDestroy()
    }
}
