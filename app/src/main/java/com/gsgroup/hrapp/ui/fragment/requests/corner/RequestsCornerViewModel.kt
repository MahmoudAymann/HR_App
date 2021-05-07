package com.gsgroup.hrapp.ui.fragment.requests.corner

import com.gsgroup.hrapp.base.BaseViewModel
import com.gsgroup.hrapp.constants.Codes

class RequestsCornerViewModel : BaseViewModel() {



    fun onRequestClick(isHr:Boolean){
        if(isHr){
            setValue(Codes.HR_REQUESTS_SCREEN)
        }else
            setValue(Codes.DM_REQUESTS_SCREEN)
    }



    fun onAuditClick(){
        setValue(Codes.AUDIT_SCREEN)

    }

    fun onMyRequestsClick(){
        setValue(Codes.MY_REQUESTS_SCREEN)
    }

}