package com.gsgroup.hrapp.ui.fragment.company_polices

import android.app.Application
import androidx.databinding.ObservableBoolean
import com.gsgroup.hrapp.base.AndroidBaseViewModel
import com.gsgroup.hrapp.util.Resource
import com.gsgroup.hrapp.util.requestNewCallDeferred

class CompanyPoliciesViewModel(app: Application) : AndroidBaseViewModel(app) {
    val adapter = CompanyPolicyAdapter(::onItemClick)
    val obsShowEmptyView = ObservableBoolean()

    private fun onItemClick(item: CompanyPolicyItem) {

    }

    init {
        requestNewCallDeferred({ policesCall() }) {
            it.response?.data?.let { list ->
                if(list.isEmpty()){
                    obsShowEmptyView.set(true)
                    adapter.clearCurrentList()
                }else {
                    obsShowEmptyView.set(false)
                    adapter.setList(list)
                }
            } ?: obsShowEmptyView.set(true)
            postResult(Resource.success(it))
        }
    }

    private fun policesCall() = apiHelper.getPoliciesAsync()
}