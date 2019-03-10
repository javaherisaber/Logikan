package ir.logicfan.core.ui.base

import android.content.Context
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import butterknife.ButterKnife
import butterknife.Unbinder
import dagger.android.support.DaggerFragment
import ir.logicfan.core.di.qulifier.ActivityContext
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment() {

    @Inject
    @ActivityContext
    lateinit var activityContext: Context

    private var butterKnifeUnbinder: Unbinder? = null

    protected fun addFragment(@IdRes containerViewId: Int, fragment: Fragment) {
        childFragmentManager.beginTransaction()
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
