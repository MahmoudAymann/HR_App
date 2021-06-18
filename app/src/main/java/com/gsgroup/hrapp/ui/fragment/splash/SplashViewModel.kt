package com.gsgroup.hrapp.ui.fragment.splash

import android.app.Application
import androidx.databinding.ObservableBoolean
import com.gsgroup.hrapp.base.AndroidBaseViewModel
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.constants.ConstString
import com.gsgroup.hrapp.ui.fragment.login.DataUser
import timber.log.Timber

class SplashViewModel(app: Application) : AndroidBaseViewModel(app) {

    val obsHideChild = ObservableBoolean(false)

    fun startNavigation() {
        Timber.e("$userData")
        userData?.id?.let {
            postValue(Codes.HOME_SCREEN)
        } ?: postValue(Codes.LOGIN_SCREEN)

    }

}