package com.gsgroup.hrapp.ui.activity.details

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.AndroidBaseViewModel
import com.gsgroup.hrapp.constants.Codes

class DetailsViewModel(app: Application) : AndroidBaseViewModel(app) {

    val obsTitle = ObservableField<String>()
    var showProgressBar = ObservableBoolean()
    val obsShowBackButton = ObservableBoolean()
    val obsShowHeaderView = ObservableBoolean()
    val obsShowToolbar = ObservableBoolean()
    val obsShowTitleBar = ObservableBoolean()


    fun onBackClick() {
        setValue(Codes.BACK_BUTTON_PRESSED)
    }

    fun setScreenPermissions(id: Int) {
        when (id) {
            R.id.splashFragment, R.id.loginFragment -> authScreenPermissions()
            R.id.mapFragment, R.id.bottomSheetFragment-> fullScreenPermission()
            else -> detailsScreen()
        }
    }

    private fun fullScreenPermission() {
        obsShowHeaderView.set(false)
    }

    private fun authScreenPermissions() { //hide all headers
        obsShowBackButton.set(false)
        obsShowHeaderView.set(false)
    }

    private fun detailsScreen() {// show back button with logo in toolbar
        obsShowBackButton.set(true)
        obsShowHeaderView.set(true)
    }


}