package ir.logicbase.core.ui.base

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.annotation.CallSuper
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import ir.logicbase.core.R
import ir.logicbase.core.data.entity.UpdateData
import ir.logicbase.core.data.error.DataException
import ir.logicbase.core.data.sharedpreferences.BaseSharedPreferences
import ir.logicbase.core.databinding.CoreViewNetworkUnavailableBinding
import ir.logicbase.core.ui.feature.main.NetworkConnectivityReceiver
import ir.logicbase.core.ui.feature.main.NetworkConnectivityViewModel
import ir.logicbase.core.ui.util.ConfigurationUtils
import javax.inject.Inject

/**
 * All of our activities extends this class to inherit top level functionality
 */
abstract class BaseActivity : AppCompatActivity(), HasAndroidInjector, DataTerminalStateListener {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var networkConnectivityReceiver: NetworkConnectivityReceiver
    @Inject
    lateinit var baseSharedPreferences: BaseSharedPreferences

    protected val networkConnectivityViewModel: NetworkConnectivityViewModel by viewModels()
    open fun attachBaseViewModel(): List<BaseViewModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this) // inject dagger
        super.onCreate(savedInstanceState)
        ConfigurationUtils.createConfigContext(this) // update locale when activity starts
        networkConnectivityReceiver.observe(this, {
            if (it) {
                // network connection is active now
                networkConnectivityViewModel.onNetworkAvailabilityChange(true)
            }
        })

        // handle terminal errors
        val baseViewModels = attachBaseViewModel()
        baseViewModels?.forEach { viewModel ->
            viewModel.errorState.observe(this, {
                onDataTerminalError(it)
            })
            viewModel.updateState.observe(this, {
                onUpdateState(it)
            })
        }
        networkConnectivityViewModel.networkBecomesAvailable.observe(this, {
            baseViewModels?.forEach { viewModel ->
                viewModel.onNetworkBecomesAvailable()
            }
        })

        networkConnectivityViewModel.alertPanelVisibility.observe(this, { isVisible ->
            val container = attachGlobalAlertContainer() ?: return@observe
            if (container.childCount > 0) {
                container.removeAllViews()
            }
            if (isVisible) {
                val viewBinding = DataBindingUtil.inflate<CoreViewNetworkUnavailableBinding>(
                    layoutInflater, attachNetworkAvailabilityLayoutRes(), container, true
                )
                viewBinding.onCloseClickListener = View.OnClickListener { networkConnectivityViewModel.onCloseClick() }
            } else {
                container.removeAllViews()
            }
        })
    }

    open fun attachGlobalAlertContainer(): ViewGroup? = null

    @Suppress("MemberVisibilityCanBePrivate")
    protected fun attachNetworkAvailabilityLayoutRes(): Int = R.layout.core_view_network_unavailable

    @CallSuper
    override fun onDataTerminalError(throwable: Throwable) {
        if (throwable is DataException.Offline) {
            networkConnectivityViewModel.onNetworkAvailabilityChange(false)
        }
    }

    @CallSuper
    override fun onUpdateState(updateData: UpdateData) {
        // do nothing here, consumer needs to handle this
    }

    @MainThread
    inline fun <reified VM : ViewModel> ComponentActivity.viewModels(
        noinline factoryProducer: (() -> ViewModelProvider.Factory)? = { viewModelFactory }
    ): Lazy<VM> {
        val factoryPromise = factoryProducer ?: {
            val application = application ?: throw IllegalArgumentException(
                "ViewModel can be accessed only when Activity is attached"
            )
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        }

        return ViewModelLazy(VM::class, { viewModelStore }, factoryPromise)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}
