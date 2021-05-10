package com.gsgroup.hrapp.ui.fragment.requests.autharea

import android.app.Application
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.AndroidBaseViewModel
import com.gsgroup.hrapp.ui.fragment.requests.phoneissue.PhoneIssueRequestRequest
import com.gsgroup.hrapp.util.Resource
import com.gsgroup.hrapp.util.requestNewCallDeferred

class AuthAreaViewModel(app: Application) : AndroidBaseViewModel(app) {


    val request = AuthAreaRequestRequest()

    fun onSubmitClick() {
        if(request.isValid()){
        requestNewCallDeferred({ authAreaRequestCallAsync() }) {
            postResult(Resource.success(msg = it.message))
        }
        }else{
            postResult(Resource.message(app.getString(R.string.all_data_required)))
        }
    }

    private fun authAreaRequestCallAsync() = apiHelper.authAreaRequestAsync(request)




}