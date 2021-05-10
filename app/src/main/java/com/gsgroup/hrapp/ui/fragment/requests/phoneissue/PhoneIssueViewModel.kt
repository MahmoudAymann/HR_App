package com.gsgroup.hrapp.ui.fragment.requests.phoneissue

import android.app.Application
import com.gsgroup.hrapp.base.AndroidBaseViewModel
import com.gsgroup.hrapp.util.Resource
import com.gsgroup.hrapp.util.requestNewCallDeferred

class PhoneIssueViewModel(app: Application) : AndroidBaseViewModel(app) {


    val request = PhoneIssueRequestRequest()

    fun onSubmitClick() {
        requestNewCallDeferred({ phoneIssueCallAsync() }) {
            postResult(Resource.success(msg = it.message))
        }
    }

    private fun phoneIssueCallAsync() = apiHelper.phoneNumberRequestAsync(request)


}