package com.gsgroup.hrapp.ui.fragment.requests.salaryinfo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.databinding.FragmentSalaryInfoBinding

class SalaryInfoFragment : BaseFragment<FragmentSalaryInfoBinding, SalaryInfoViewModel>() {
    override fun pageTitle(): String = ""
    override val mViewModel: SalaryInfoViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}