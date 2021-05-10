package com.gsgroup.hrapp.ui.fragment.requests.printinsurance

import android.app.Application
import com.gsgroup.hrapp.base.AndroidBaseViewModel
import com.gsgroup.hrapp.util.Resource
import com.gsgroup.hrapp.util.requestNewCallDeferred

class PrintInsuranceViewModel(app: Application) : AndroidBaseViewModel(app) {

    fun onSubmitClick(){
        requestNewCallDeferred({ printInsuranceCallAsync() }) {
            postResult(Resource.success(msg = it.message))
        }
    }

    private fun printInsuranceCallAsync() = apiHelper.printInsuranceRequestAsync()

}