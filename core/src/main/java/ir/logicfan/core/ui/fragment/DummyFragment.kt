package ir.logicfan.core.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import ir.logicfan.core.R
import ir.logicfan.core.databinding.FragmentDummyBinding
import ir.logicfan.core.ui.base.BaseFragment
import ir.logicfan.core.ui.base.BaseViewModel
import ir.logicfan.core.ui.viewmodel.DummyViewModel
import javax.inject.Inject

class DummyFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: DummyViewModel

    override fun attachBaseViewModel(): BaseViewModel = viewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<FragmentDummyBinding>(inflater, R.layout.fragment_dummy, container, false)
            .apply {
                setLifecycleOwner(this@DummyFragment)
                viewModel = this@DummyFragment.viewModel
            }.root
    }

    companion object {
        fun newInstance(): DummyFragment = DummyFragment()
    }
}