package com.gsgroup.hrapp.ui.fragment.requests.myrequest


import android.app.Application
import com.gsgroup.hrapp.base.AndroidBaseViewModel
import com.gsgroup.hrapp.constants.Codes

class MyRequestsViewModel(app : Application) : AndroidBaseViewModel(app) {


    fun onButtonClick(isMine : Boolean) {
        if(isMine){
            setValue(Codes.MY_REQUESTS_SCREEN)
        }else{
            setValue(Codes.REQUESTS_SENT_TO_ME_SCREEN)
        }
    }

}