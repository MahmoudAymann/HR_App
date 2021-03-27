package com.gsgroup.hrapp.ui.fragment.covid

import com.gsgroup.hrapp.base.BaseViewModel
import com.gsgroup.hrapp.constants.Codes

class CovidViewModel : BaseViewModel() {


    fun onNewsClick(){
        setValue(Codes.NEWS_SCREEN)
    }


    fun onInfectedClick(){
        setValue(Codes.INFECT_SCREEN)
    }

}