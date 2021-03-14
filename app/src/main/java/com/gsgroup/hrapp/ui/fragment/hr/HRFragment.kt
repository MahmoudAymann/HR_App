package com.gsgroup.hrapp.ui.fragment.hr

import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.databinding.FragmentDirectManagerBinding
import com.gsgroup.hrapp.databinding.FragmentHrBinding
import com.gsgroup.hrapp.ui.fragment.direct_manager.HRViewModel

class HRFragment : BaseFragment<FragmentHrBinding, HRViewModel>() {
    override fun pageTitle(): String = ""
    override val mViewModel: HRViewModel by viewModels()
}