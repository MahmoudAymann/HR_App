package com.gsgroup.hrapp.ui.activity

import android.content.Context
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.util.AppUtil.getDrawableForLiteVersions
import com.tenclouds.fluidbottomnavigation.FluidBottomNavigationItem

object MainModel {
    fun getBottomNavBarList(app: Context): List<FluidBottomNavigationItem> =
        listOf(
            FluidBottomNavigationItem(
                app.getString(R.string.home),
                app.getDrawableForLiteVersions(R.drawable.ic_home)
            ),
            FluidBottomNavigationItem(
                app.getString(R.string.chat),
                app.getDrawableForLiteVersions(R.drawable.ic_chatting)
            ),
            FluidBottomNavigationItem(
                app.getString(R.string.notification),
                app.getDrawableForLiteVersions(R.drawable.ic_notification)
            ),
            FluidBottomNavigationItem(
                app.getString(R.string.faq),
                app.getDrawableForLiteVersions(R.drawable.ic_faq)
            ),
            FluidBottomNavigationItem(
                app.getString(R.string.settings),
                app.getDrawableForLiteVersions(R.drawable.ic_settings)
            )
        )

}