package com.gsgroup.hrapp.ui.fragment.direct_manager

import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.databinding.FragmentDirectManagerBinding

class DirectManagerFragment : BaseFragment<FragmentDirectManagerBinding, HRViewModel>() {
    override fun pageTitle(): String = ""
    override val mViewModel: HRViewModel by viewModels()
}