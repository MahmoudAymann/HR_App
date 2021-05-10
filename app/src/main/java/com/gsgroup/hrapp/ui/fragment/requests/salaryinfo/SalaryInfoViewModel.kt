package com.gsgroup.hrapp.ui.fragment.requests.salaryinfo

import android.app.Application
import com.gsgroup.hrapp.base.AndroidBaseViewModel
import com.gsgroup.hrapp.util.Resource
import com.gsgroup.hrapp.util.requestNewCallDeferred

class SalaryInfoViewModel(app:Application) : AndroidBaseViewModel(app) {

    val request = SalaryInfoRequestRequest()

    fun onSubmitClick() {
        requestNewCallDeferred({ salaryInfoCallAsync() }) {
            postResult(Resource.success(msg = it.message))
        }
    }

    private fun salaryInfoCallAsync() = apiHelper.salaryInfoRequestAsync(request)



}