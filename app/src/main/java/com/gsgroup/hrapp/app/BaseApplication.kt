package com.gsgroup.hrapp.app

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.gsgroup.hrapp.util.LocalUtil
import timber.log.Timber
import timber.log.Timber.DebugTree

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initTimber()
        DataBindingUtil.setDefaultComponent(AppDataBindingComponent())
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }


    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocalUtil.onAttach(base))
        MultiDex.install(this)
    }


    private fun initTimber() {
        Timber.plant(object : DebugTree() {
            override fun createStackElementTag(element: StackTraceElement): String {
                return super.createStackElementTag(element) + "line: " + element.lineNumber
            }
        })
    }

}