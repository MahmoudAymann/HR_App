package com.gsgroup.hrapp.app

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import timber.log.Timber
import timber.log.Timber.DebugTree

class BaseApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        initTimber()
        DataBindingUtil.setDefaultComponent(AppDataBindingComponent())
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    private fun initTimber() {
        Timber.plant(object : DebugTree() {
            override fun createStackElementTag(element: StackTraceElement): String? {
                return super.createStackElementTag(element) + "line: " + element.lineNumber
            }
        })
    }
}