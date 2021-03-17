package com.gsgroup.hrapp.ui.fragment.company_polices

import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseAdapter
import com.gsgroup.hrapp.base.BaseViewHolder

class CompanyPolicyAdapter(itemClick: (CompanyPolicyItem) -> Unit) : BaseAdapter<CompanyPolicyItem>(itemClick) {

    override fun layoutRes(): Int = R.layout.item_company_policy_view
    override fun onViewHolderCreated(viewHolder: BaseViewHolder<CompanyPolicyItem>) {

    }
}