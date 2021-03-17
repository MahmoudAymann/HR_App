package com.gsgroup.hrapp.ui.activity

import android.app.Application
import androidx.core.content.ContextCompat
import com.gsgroup.hrapp.R
import com.tenclouds.fluidbottomnavigation.FluidBottomNavigationItem

object MainModel {
    fun getBottomNavBarList(app: Application): List<FluidBottomNavigationItem> = listOf(
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

}