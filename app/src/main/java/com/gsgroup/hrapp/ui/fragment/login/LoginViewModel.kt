package com.gsgroup.hrapp.ui.fragment.login

import android.app.Application
import androidx.databinding.ObservableBoolean
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.AndroidBaseViewModel
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.constants.ConstString
import com.gsgroup.hrapp.util.Resource
import com.gsgroup.hrapp.util.SharedPrefUtil.getPrefLanguage
import com.gsgroup.hrapp.util.SharedPrefUtil.setData
import com.gsgroup.hrapp.util.SharedPrefUtil.setPrefLanguage
import com.gsgroup.hrapp.util.SharedPrefUtil.sharedPrefs
import com.gsgroup.hrapp.util.requestNewCallDeferred

class LoginViewModel(app: Application) : AndroidBaseViewModel(app) {
    val obsIsArabic = ObservableBoolean(app.getPrefLanguage() == ConstString.LANG_AR)
    private var selectedLang = ""
    val request = LoginRequest()
    val obsIsRememberMe = ObservableBoolean()
    val rememberMeRequest by app.sharedPrefs<RememberMeRequest>(ConstString.PREF_USER_LOGIN_SECURITY_DATA)


    fun onLangClick(isArabic: Boolean) {
        selectedLang = if (isArabic) ConstString.LANG_AR else ConstString.LANG_EN
        if (app.getPrefLanguage() != selectedLang) {
            setValue(Codes.CHANGE_LANG)
        }
    }

    init {
        request.email = rememberMeRequest?.email
        request.password = rememberMeRequest?.password
        notifyChange()
    }

    fun changeLang() {
        app.setPrefLanguage(selectedLang)
        setValue(Codes.RESTART_APP)
    }

    fun onLoginClick() {
        if (request.isValid()) {
            requestNewCallDeferred({ loginCallAsync() }) {
                saveUserDataInPrefs(it.response?.dataUser)
                postResult(Resource.success(it))
            }
        } else {
            postResult(Resource.message(app.getString(R.string.all_data_required)))
        }
    }

    private fun saveUserDataInPrefs(data: DataUser?) {
        app.sharedPrefs<DataUser>(ConstString.PREF_USER_DATA).setData((data))
        if (obsIsRememberMe.get()) {
            rememberMeRequest?.email = request.email
            rememberMeRequest?.password = request.password
            app.sharedPrefs<RememberMeRequest>(ConstString.PREF_USER_LOGIN_SECURITY_DATA).setData((rememberMeRequest))
        }
    }

    private fun loginCallAsync() = apiHelper.loginAsync(request)


    fun onRememberMeClick(){
        if(obsIsRememberMe.get()){
            obsIsRememberMe.set(false)
        }else{
            obsIsRememberMe.set(true)
        }
    }

}