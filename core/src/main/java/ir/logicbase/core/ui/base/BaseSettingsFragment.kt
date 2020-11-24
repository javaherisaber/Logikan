package ir.logicbase.core.ui.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.CallSuper
import androidx.annotation.MainThread
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.preference.PreferenceFragmentCompat
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import ir.logicbase.core.ui.feature.main.NetworkConnectivityViewModel
import ir.logicbase.core.ui.util.delegate.autoClearedActivityListener
import javax.inject.Inject

abstract class BaseSettingsFragment : PreferenceFragmentCompat(), BaseFragmentCompat {

    @Inject
    override lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    override lateinit var viewModelFactory: ViewModelProvider.Factory

    override val dataTerminalStateListener: DataTerminalStateListener by autoClearedActivityListener()
    override val networkConnectivityViewModel: NetworkConnectivityViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        handleBaseViewModelsError(viewLifecycleOwner)
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy callback on : ${this.javaClass.simpleName}")
        super.onDestroy()
    }

    @MainThread
    inline fun <reified VM : ViewModel> viewModels(
        noinline ownerProducer: () -> ViewModelStoreOwner = { this },
        noinline factoryProducer: (() -> ViewModelProvider.Factory)? = { viewModelFactory }
    ) = createViewModelLazy(VM::class, { ownerProducer().viewModelStore }, factoryProducer)

    @MainThread
    inline fun <reified VM : ViewModel> activityViewModels(
        noinline factoryProducer: (() -> ViewModelProvider.Factory)? = { viewModelFactory }
    ) = createViewModelLazy(VM::class, { requireActivity().viewModelStore }, factoryProducer)

    companion object {
        val TAG = BaseSettingsFragment::class.java.simpleName
    }
}