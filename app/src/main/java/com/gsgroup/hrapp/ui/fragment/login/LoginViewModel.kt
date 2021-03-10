package com.gsgroup.hrapp.ui.fragment.login

import com.gsgroup.hrapp.base.BaseViewModel
import com.gsgroup.hrapp.constants.Codes

class LoginViewModel:BaseViewModel() {

    fun onLoginClick(){
        setValue(Codes.HOME_SCREEN)
    }




}