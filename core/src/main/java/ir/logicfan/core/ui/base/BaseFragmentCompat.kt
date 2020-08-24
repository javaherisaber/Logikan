package ir.logicfan.core.ui.base

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import ir.logicfan.core.ui.feature.main.NetworkConnectivityViewModel

interface BaseFragmentCompat : HasAndroidInjector {
    var androidInjector: DispatchingAndroidInjector<Any>
    var viewModelFactory: ViewModelProvider.Factory
    val dataTerminalStateListener: DataTerminalStateListener
    val networkConnectivityViewModel: NetworkConnectivityViewModel

    fun attachBaseViewModel(): List<BaseViewModel>? = null

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    fun handleBaseViewModelsError(lifecycleOwner: LifecycleOwner) {
        val baseViewModels = attachBaseViewModel()
        baseViewModels?.forEach { viewModel ->
            viewModel.errorState.observe(lifecycleOwner, Observer {
                dataTerminalStateListener.onDataTerminalError(it)
            })
            viewModel.updateState.observe(lifecycleOwner, Observer {
                dataTerminalStateListener.onUpdateState(it)
            })
        }
        networkConnectivityViewModel.networkBecomesAvailable.observe(lifecycleOwner, Observer {
            baseViewModels?.forEach { viewModel ->
                viewModel.onNetworkBecomesAvailable()
            }
        })
    }
}