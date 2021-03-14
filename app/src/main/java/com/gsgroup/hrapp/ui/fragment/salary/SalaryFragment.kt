package com.gsgroup.hrapp.ui.fragment.salary

import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.databinding.FragmentSalaryBinding

class SalaryFragment : BaseFragment<FragmentSalaryBinding, SalaryViewModel>() {
    override fun pageTitle(): String = getString(R.string.salary_info)
    override val mViewModel: SalaryViewModel by viewModels()

}