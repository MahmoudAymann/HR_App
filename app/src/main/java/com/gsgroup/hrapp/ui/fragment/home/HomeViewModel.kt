package com.gsgroup.hrapp.ui.fragment.home

import android.app.Application
import androidx.databinding.ObservableBoolean
import com.gsgroup.hrapp.base.AndroidBaseViewModel
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.data.model.HomeItem

class HomeViewModel(app: Application) : AndroidBaseViewModel(app) {

    val adapter = HomeAdapter(::onAdapterItemClick)
    val obsIsCheckedIn = ObservableBoolean()
    private fun onAdapterItemClick(item: HomeItem) {
        setValue(item.id)
    }

    fun onImageClick() {

    }


    init {
        adapter.setList(HomeItem.getHomeList(app))
    }


    fun onLoginLogsClick(isCheckIn: Boolean) {
        if (isCheckIn)
            setValue(Codes.CHECK_IN)
        else
            setValue(Codes.CHECK_OUT)
    }


    fun checkForCheckIn(){
        userData?.attendance?.timeIn?.let {
            obsIsCheckedIn.set(true)
        }?:obsIsCheckedIn.set(false)
    }
}