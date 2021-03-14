package com.gsgroup.hrapp.ui.fragment.company_polices

import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.databinding.FragmentCompanyPoliciesBinding

class CompanyPoliciesFragment :
    BaseFragment<FragmentCompanyPoliciesBinding, CompanyPoliciesViewModel>() {
    override fun pageTitle(): String = ""
    override val mViewModel: CompanyPoliciesViewModel by viewModels()
}