package com.gsgroup.hrapp.ui.activity

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.AndroidBaseViewModel
import com.gsgroup.hrapp.constants.Codes

class MainViewModel(app: Application) : AndroidBaseViewModel(app) {

    val obsTitle = ObservableField<String>()
    val obsShowMainToolbarIcons = ObservableBoolean()
    val obsShowBackButton = ObservableBoolean()
    val obsShowBottomBar = ObservableBoolean()
    val obsShowHeaderView = ObservableBoolean()
    val bottomBarMenuItems = MainModel.getBottomNavBarList(app)

    fun onSalaryClick() {
        setValue(Codes.SALARY_SCREEN)
    }

    fun onCovidClick() {
        setValue(Codes.COVID_SCREEN)
    }

    fun onLogoutClick() {
        setValue(Codes.LOG_OUT)
    }

    fun onBackClick() {
        setValue(Codes.BACK_BUTTON_PRESSED)
    }

    fun setScreenPermissions(id: Int) {
        when (id) {
            R.id.splashFragment, R.id.loginFragment -> authScreenPermissions()
            R.id.homeFragment, R.id.chatFragment, R.id.notificationFragment, R.id.faqFragment, R.id.settingsFragment -> mainScreenPermissions()
            else -> {
                detailsScreen()
            }
        }
    }

    private fun authScreenPermissions() { //hide all headers
        obsShowBottomBar.set(false)
        obsShowBackButton.set(false)
        obsShowMainToolbarIcons.set(false)
    }

    private fun mainScreenPermissions() { //show bottom bar and toolbar with all buttons
        obsShowBottomBar.set(true)
        obsShowMainToolbarIcons.set(true)
        obsShowBackButton.set(false)
        obsShowHeaderView.set(true)

    }

    private fun detailsScreen() {// show back button with logo in toolbar
        obsShowBottomBar.set(false)
        obsShowMainToolbarIcons.set(false)
        obsShowBackButton.set(true)
        obsShowHeaderView.set(true)
    }


}