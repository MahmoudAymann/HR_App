package com.gsgroup.hrapp.ui.fragment.company_polices

import androidx.databinding.ObservableBoolean
import com.gsgroup.hrapp.base.BaseViewModel

class CompanyPoliciesViewModel : BaseViewModel() {
    val showNoData = ObservableBoolean()
    val adapter = CompanyPolicyAdapter(::onItemClick)

    private fun onItemClick(item: CompanyPolicyItem) {


    }

    init {
        adapter.setList(CompanyPolicyItem.getDummyList())
    }
}