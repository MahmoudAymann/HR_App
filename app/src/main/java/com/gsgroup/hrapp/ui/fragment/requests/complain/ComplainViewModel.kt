package com.gsgroup.hrapp.ui.fragment.requests.complain

import android.app.Application
import com.gsgroup.hrapp.base.AndroidBaseViewModel
import com.gsgroup.hrapp.util.Resource
import com.gsgroup.hrapp.util.requestNewCallDeferred

class ComplainViewModel(app: Application) : AndroidBaseViewModel(app) {

    val request = ComplainRequest()


    fun gotData(){

    }

    fun onSubmitClick() {
        if(request.isValid()) {
            requestNewCallDeferred({ complainCallAsync() }) {
            }
        }
    }


    private fun complainCallAsync() = apiHelper.complainAsync(request)

}