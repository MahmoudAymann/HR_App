package com.gsgroup.hrapp.ui.activity

import android.app.Application
import androidx.core.content.ContextCompat
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.AndroidBaseViewModel
import com.tenclouds.fluidbottomnavigation.FluidBottomNavigationItem

class MainViewModel(app: Application) : AndroidBaseViewModel(app) {

    val obsTitle = ObservableField<String>()
    val obsShowMainToolbar = ObservableBoolean()
    val obsShowBackButton = ObservableBoolean()
    val obsShowBottomBar = ObservableBoolean(true)
    val bottomBarMenuItems = listOf(
        FluidBottomNavigationItem(
            app.getString(R.string.home),
            ContextCompat.getDrawable(app, R.drawable.ic_home)
        ),
        FluidBottomNavigationItem(
            app.getString(R.string.chat),
            ContextCompat.getDrawable(app, R.drawable.ic_chatting)
        ),
        FluidBottomNavigationItem(
            app.getString(R.string.notification),
            ContextCompat.getDrawable(app, R.drawable.ic_notification)
        ),
        FluidBottomNavigationItem(
            app.getString(R.string.faq),
            ContextCompat.getDrawable(app, R.drawable.ic_faq)
        ),
        FluidBottomNavigationItem(
            app.getString(R.string.settings),
            ContextCompat.getDrawable(app, R.drawable.ic_settings)
        )
    )

    fun onSalaryClick() {

    }

    fun onCovidClick() {

    }

    fun onLogoutClick() {

    }

    fun onBackClick() {

    }




}