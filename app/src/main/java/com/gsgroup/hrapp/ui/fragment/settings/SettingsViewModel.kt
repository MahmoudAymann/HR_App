package com.gsgroup.hrapp.ui.fragment.settings

import android.app.Application
import androidx.databinding.ObservableBoolean
import com.gsgroup.hrapp.base.AndroidBaseViewModel
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.constants.ConstString
import com.gsgroup.hrapp.util.SharedPrefUtil.getPrefLanguage
import com.gsgroup.hrapp.util.SharedPrefUtil.setPrefLanguage

class SettingsViewModel( app: Application) : AndroidBaseViewModel(app) {
    val obsIsArabic = ObservableBoolean(app.getPrefLanguage() == ConstString.LANG_AR)
    private var selectedLang = ""

    fun onLangClick(isArabic: Boolean) {
        selectedLang = if (isArabic) ConstString.LANG_AR else ConstString.LANG_EN
        if (app.getPrefLanguage() != selectedLang) {
            setValue(Codes.CHANGE_LANG)
        }
    }

    fun changeLang() {
        app.setPrefLanguage(selectedLang)
        setValue(Codes.RESTART_APP)
    }


    fun onChangePasswordClick(){
        setValue(Codes.CHANGE_PASSWORD_SCREEN)
    }
    fun onLogoutClick(){
        setValue(Codes.LOG_OUT)
    }
}