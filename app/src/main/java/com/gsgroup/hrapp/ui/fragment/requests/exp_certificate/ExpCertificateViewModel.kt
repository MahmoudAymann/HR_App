package com.gsgroup.hrapp.ui.fragment.requests.exp_certificate

import android.app.Application
import com.gsgroup.hrapp.base.AndroidBaseViewModel
import com.gsgroup.hrapp.util.Resource
import com.gsgroup.hrapp.util.requestNewCallDeferred

class ExpCertificateViewModel(app: Application) : AndroidBaseViewModel(app) {

    fun onSendClick(){
        requestNewCallDeferred({ expCertificateCallAsync() }) {
            postResult(Resource.success(msg = it.message))
        }
    }


    private fun expCertificateCallAsync() = apiHelper.expCertificateRequestAsync()


}