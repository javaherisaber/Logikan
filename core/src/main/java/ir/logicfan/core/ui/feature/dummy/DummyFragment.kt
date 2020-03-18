/*
 * Copyright (C) Logicfan, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential
 * Written by Mahdi Javaheri Saber <m.javaherisaber@gmail.com>, Mohammad Hosein Abedini <mohammadhoseinabedini@gmail.com>  2020.
 */

package ir.logicfan.core.ui.feature.dummy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import ir.logicfan.core.R
import ir.logicfan.core.databinding.CoreFragmentDummyBinding
import ir.logicfan.core.ui.base.BaseFragment

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