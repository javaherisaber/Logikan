package ir.logicbase.core.ui.feature.dummy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import ir.logicbase.core.R
import ir.logicbase.core.databinding.CoreFragmentDummyBinding
import ir.logicbase.core.ui.base.BaseFragment

class DummyFragment : BaseFragment() {

    private val viewModel: DummyViewModel by viewModels()

    override fun attachBaseViewModel() = listOf(viewModel)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        DataBindingUtil.inflate<CoreFragmentDummyBinding>(inflater, R.layout.core_fragment_dummy, container, false)
            .apply {
                lifecycleOwner = this@DummyFragment.viewLifecycleOwner
                viewModel = this@DummyFragment.viewModel
            }.root
}