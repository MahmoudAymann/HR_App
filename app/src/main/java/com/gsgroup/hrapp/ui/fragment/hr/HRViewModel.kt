package com.gsgroup.hrapp.ui.fragment.hr

import android.app.Application
import com.gsgroup.hrapp.base.AndroidBaseViewModel
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.data.model.DirectData
import com.gsgroup.hrapp.util.Resource
import com.gsgroup.hrapp.util.requestNewCallDeferred

class HRViewModel(app: Application) : AndroidBaseViewModel(app) {

    var data: DirectData? = null



    fun onPhoneCall(isPrivate: Boolean) {
        setValue(if (isPrivate) Codes.PRIVATE_PHONE else Codes.BUSINESS_PHONE)
    }


    fun onEmailClick() {
        setValue(Codes.EMAIL_CLICK)
    }


    init {
        isLoading.set(true)
        requestNewCallDeferred({ hrCall() }) {
            postResult(Resource.success(it))
            data = it.response?.data
            isLoading.set(false)
            notifyChange()
        }
    }

    private fun hrCall() = apiHelper.getDirectHrAsync()



}