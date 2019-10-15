package ir.logicfan.core.ui.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import ir.logicfan.core.ui.util.delegate.autoClearedActivityListener
import javax.inject.Inject

abstract class BaseFragment : Fragment(), HasAndroidInjector {

    private val dataTerminalErrorListener by autoClearedActivityListener<DataTerminalErrorListener>()

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    abstract fun attachBaseViewModel(): BaseViewModel

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    @CallSuper // a standard annotation from AndroidX library, forcing derived function to call super
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        attachBaseViewModel().errorState.observe(this, Observer {
            dataTerminalErrorListener.onDataTerminalError(it)
        })
    }

    /**
     * Propagate error from rx callbacks to suitable error resolver
     */
    interface DataTerminalErrorListener {
        fun onDataTerminalError(throwable: Throwable)
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy callback on : ${this.javaClass.simpleName}")
        super.onDestroy()
    }

    companion object {
        private var TAG = BaseFragment::class.java.simpleName
    }

    fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
