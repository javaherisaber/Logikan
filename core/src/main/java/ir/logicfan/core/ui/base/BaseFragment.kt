package ir.logicfan.core.ui.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.CallSuper
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import ir.logicfan.core.ui.feature.main.NetworkConnectivityViewModel
import ir.logicfan.core.ui.util.delegate.autoClearedActivityListener
import javax.inject.Inject

abstract class BaseFragment : Fragment(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val dataTerminalErrorListener by autoClearedActivityListener<DataTerminalErrorListener>()
    private val networkConnectivityViewModel: NetworkConnectivityViewModel by activityViewModels()

    open fun attachBaseViewModel(): List<BaseViewModel>? = null

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    @CallSuper // a standard annotation from AndroidX library, forcing derived function to call super
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val baseViewModels = attachBaseViewModel()
        baseViewModels?.forEach { viewModel ->
            viewModel.errorState.observe(viewLifecycleOwner, Observer {
                dataTerminalErrorListener.onDataTerminalError(it)
            })
        }
        networkConnectivityViewModel.networkBecomesAvailable.observe(viewLifecycleOwner, Observer {
            baseViewModels?.forEach { viewModel ->
                viewModel.onNetworkBecomesAvailable()
            }
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

    @MainThread
    inline fun <reified VM : ViewModel> Fragment.viewModels(
        noinline ownerProducer: () -> ViewModelStoreOwner = { this },
        noinline factoryProducer: (() -> ViewModelProvider.Factory)? = { viewModelFactory }
    ) = createViewModelLazy(VM::class, { ownerProducer().viewModelStore }, factoryProducer)

    @MainThread
    inline fun <reified VM : ViewModel> Fragment.activityViewModels(
        noinline factoryProducer: (() -> ViewModelProvider.Factory)? = { viewModelFactory }
    ) = createViewModelLazy(VM::class, { requireActivity().viewModelStore }, factoryProducer)

    companion object {
        private var TAG = BaseFragment::class.java.simpleName
    }
}
