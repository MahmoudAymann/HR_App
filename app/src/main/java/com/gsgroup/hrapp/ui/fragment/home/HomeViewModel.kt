package com.gsgroup.hrapp.ui.fragment.home

import com.gsgroup.hrapp.base.BaseViewModel
import com.gsgroup.hrapp.model.HomeItem

class HomeViewModel : BaseViewModel() {

    val adapter = HomeAdapter(::onAdapterItemClick)


    private fun onAdapterItemClick(item: HomeItem){

    }

    fun onImageClick(){

    }

}