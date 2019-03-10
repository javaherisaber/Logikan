package ir.logicfan.core.ui.base

import android.content.Context
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import butterknife.ButterKnife
import butterknife.Unbinder
import dagger.android.support.DaggerFragment
import ir.logicfan.core.di.qulifier.ActivityContext
import ir.logicfan.core.di.qulifier.FragmentChildFragmentManager

import javax.inject.Inject

abstract class BaseFragment : DaggerFragment() {

    @Inject
    @ActivityContext
    lateinit var activityContext: Context

    @Inject
    @FragmentChildFragmentManager
    lateinit var mChildFragmentManager: FragmentManager

    private var butterKnifeUnbinder: Unbinder? = null

    protected fun addFragment(@IdRes containerViewId: Int, fragment: Fragment) {
        mChildFragmentManager.beginTransaction()
            .add(containerViewId, fragment)
            .commit()
    }

    protected fun setButterKnifeUnbinder(target: Any, source: View) {
        butterKnifeUnbinder = ButterKnife.bind(target, source)
    }

    override fun onDestroy() {
        butterKnifeUnbinder?.unbind()
        super.onDestroy()
    }
}
