package ir.logicfan.core.ui.base

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.lifecycle.Observer
import dagger.android.support.DaggerFragment
import ir.logicfan.core.ui.util.delegate.autoClearedActivityListener

abstract class BaseFragment : DaggerFragment() {

    private val dataTerminalErrorListener by autoClearedActivityListener<DataTerminalErrorListener>()

    abstract fun attachBaseViewModel(): BaseViewModel

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
}
