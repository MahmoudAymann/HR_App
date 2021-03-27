package com.gsgroup.hrapp.ui.fragment.home

import android.app.Application
import com.gsgroup.hrapp.base.AndroidBaseViewModel
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.data.model.HomeItem

class HomeViewModel(val app: Application) : AndroidBaseViewModel(app) {

    val adapter = HomeAdapter(::onAdapterItemClick)

    private fun onAdapterItemClick(item: HomeItem) {
        setValue(item.id)
    }

    fun onImageClick() {

    }


    init {
        adapter.setList(HomeItem.getHomeList(app))
    }


    fun onLoginLogsClick(){
        setValue(Codes.MAP_SCREEN)
    }
}