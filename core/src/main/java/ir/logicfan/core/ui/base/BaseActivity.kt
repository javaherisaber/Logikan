package ir.logicfan.core.ui.base

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.annotation.CallSuper
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import ir.logicfan.core.R
import ir.logicfan.core.data.error.DataException
import ir.logicfan.core.data.sharedpreferences.BaseSharedPreferences
import ir.logicfan.core.databinding.CoreViewNetworkUnavailableBinding
import ir.logicfan.core.ui.feature.main.NetworkConnectivityReceiver
import ir.logicfan.core.ui.feature.main.NetworkConnectivityViewModel
import ir.logicfan.core.ui.util.LocaleUtils
import javax.inject.Inject

/**
 * All of our activities extends this class to inherit top level functionality
 */
abstract class BaseActivity : AppCompatActivity(), HasAndroidInjector, DataTerminalErrorListener {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var networkConnectivityReceiver: NetworkConnectivityReceiver
    @Inject
    lateinit var baseSharedPreferences: BaseSharedPreferences

    private val networkConnectivityViewModel: NetworkConnectivityViewModel by viewModels()
    open fun attachBaseViewModel(): List<BaseViewModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this) // inject dagger
        super.onCreate(savedInstanceState)
        LocaleUtils.setLocale(this, baseSharedPreferences.localeSetting) // setup locale
        networkConnectivityReceiver.observe(this, Observer {
            if (it) {
                // network connection is active now
                networkConnectivityViewModel.onNetworkAvailabilityChange(true)
            }
        })

        // handle terminal errors
        val baseViewModels = attachBaseViewModel()
        baseViewModels?.forEach { viewModel ->
            viewModel.errorState.observe(this, Observer {
                onDataTerminalError(it)
            })
        }
        networkConnectivityViewModel.networkBecomesAvailable.observe(this, Observer {
            baseViewModels?.forEach { viewModel ->
                viewModel.onNetworkBecomesAvailable()
            }
        })

        networkConnectivityViewModel.alertPanelVisibility.observe(this, Observer {
            val container = attachNetworkAvailabilityContainer()
            if (container != null && it) {
                val viewBinding = DataBindingUtil.inflate<CoreViewNetworkUnavailableBinding>(
                    layoutInflater, attachNetworkAvailabilityLayoutRes(), container, true
                )
                viewBinding.onCloseClickListener = View.OnClickListener { networkConnectivityViewModel.onCloseClick() }
            } else {
                container?.removeAllViews()
            }
        })
    }

    open fun attachNetworkAvailabilityContainer(): ViewGroup? = null

    protected fun attachNetworkAvailabilityLayoutRes(): Int = R.layout.core_view_network_unavailable

    @CallSuper
    override fun onDataTerminalError(throwable: Throwable) {
        if (throwable  is DataException.Terminal.Offline) {
            networkConnectivityViewModel.onNetworkAvailabilityChange(false)
        }
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
