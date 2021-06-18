package com.gsgroup.hrapp.ui.fragment.home

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.viewModelScope
import com.gsgroup.hrapp.base.AndroidBaseViewModel
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.data.model.HomeItem
import com.gsgroup.hrapp.data.remote.ApiService
import com.gsgroup.hrapp.util.isNull
import timber.log.Timber
import javax.inject.Inject

class HomeViewModel(app: Application) : AndroidBaseViewModel(app) {

    val adapter = HomeAdapter(::onAdapterItemClick)
    val obsIsCheckedIn = ObservableBoolean()
    val obsIsFullDocument = ObservableBoolean()

    private fun onAdapterItemClick(item: HomeItem) {
        setValue(item.id)
    }

    fun onImageClick() {

    }

    fun onErrorClick() {
        if (!obsIsFullDocument.get())
            setValue(Codes.MISSED_DOCUMENT)
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

    fun refreshData() {
        notifyChange() // to refresh dates
        obsIsCheckedIn.set(!userData?.attendance?.timeIn.isNullOrBlank())
        obsIsFullDocument.set(userData?.hasFulldocument == true)
    }
}