package com.gsgroup.hrapp.ui.fragment.hr

import com.gsgroup.hrapp.base.BaseViewModel
import com.gsgroup.hrapp.constants.Codes

class HRViewModel : BaseViewModel() {




    fun showBottom(){
        setValue(Codes.NEWS_SCREEN)
    }
    fun onBack(){
        setValue(Codes.BACK_BUTTON_PRESSED)
    }

}