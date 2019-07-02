package ir.logicfan.core.ui.feature.dummy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import ir.logicfan.core.R
import ir.logicfan.core.databinding.CoreFragmentDummyBinding
import ir.logicfan.core.ui.base.BaseFragment
import ir.logicfan.core.ui.base.BaseViewModel
import javax.inject.Inject

class DummyFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: DummyViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(DummyViewModel::class.java)
    }

    override fun attachBaseViewModel(): BaseViewModel = viewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        DataBindingUtil.inflate<CoreFragmentDummyBinding>(inflater, R.layout.core_fragment_dummy, container, false)
            .apply {
                lifecycleOwner = this@DummyFragment
                viewModel = this@DummyFragment.viewModel
            }.root
}