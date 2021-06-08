package com.gsgroup.hrapp.ui.fragment.login

import android.app.Application
import androidx.databinding.ObservableBoolean
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.AndroidBaseViewModel
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.constants.ConstString
import com.gsgroup.hrapp.util.BiometricUtils
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
    val obsShowBiometricButton = ObservableBoolean()

    private val rememberMeRequest by app.sharedPrefs<RememberMeRequest>(ConstString.PREF_USER_LOGIN_REMEMBER_ME_DATA)
    private val biometricRequest by app.sharedPrefs<BiometricRequest>(ConstString.PREF_USER_LOGIN_BIOMETRIC_DATA)
    private val dontAskForBiometric by app.sharedPrefs<Boolean>(ConstString.PREF_DONT_ASK_AGAIN_BIO)

    fun onLangClick(isArabic: Boolean) {
        selectedLang = if (isArabic) ConstString.LANG_AR else ConstString.LANG_EN
        if (app.getPrefLanguage() != selectedLang) {
            setValue(Codes.CHANGE_LANG)
        }
    }

    fun onBiometricClick() {
       setValue(Codes.LOGIN_WITH_BIOMETRIC)
    }

    fun loginWithBiometricData(){
        request.natId = biometricRequest?.natId
        request.password = biometricRequest?.password
        doLogin()
    }

    init {
        //to get data from Remember me
        request.natId = rememberMeRequest?.natId
        request.password = rememberMeRequest?.password
        notifyChange()
    }

    fun changeLang() {
        app.setPrefLanguage(selectedLang)
        setValue(Codes.RESTART_APP)
    }

    fun onLoginClick() {
        if (request.isValid()) {
            doLogin()
        } else {
            postResult(Resource.message(app.getString(R.string.all_data_required)))
        }
    }

    private fun doLogin() {
        requestNewCallDeferred({ loginCallAsync() }) {
            saveUserDataInPrefs(it.response?.dataUser)
            postResult(Resource.success())
            if(hasBiometricData()){
               postValue(Codes.HOME_SCREEN)
            }else{
                postValue(Codes.SHOW_BIOMETRIC_ASK_DIALOG)
            }
        }
    }

    private fun saveUserDataInPrefs(data: DataUser?) {
        app.sharedPrefs<DataUser>(ConstString.PREF_USER_DATA).setData(data)
        if (obsIsRememberMe.get()) {
            rememberMeRequest?.natId = request.natId
            rememberMeRequest?.password = request.password
            app.sharedPrefs<RememberMeRequest>(ConstString.PREF_USER_LOGIN_REMEMBER_ME_DATA)
                .setData((rememberMeRequest))
        }
    }

    private fun loginCallAsync() = apiHelper.loginAsync(request)


    fun onRememberMeClick() {
        if (obsIsRememberMe.get()) {
            obsIsRememberMe.set(false)
        } else {
            obsIsRememberMe.set(true)
        }
    }

    fun isBiometricHardwareAvail(code: Int) {
        when (code) {
            BiometricUtils.BiometricCodes.CAN_USE_BIOMETRIC -> {
                if (dontAskForBiometric == true)
                    obsShowBiometricButton.set(false)
                else
                    obsShowBiometricButton.set(true)
            }
            else -> {
                obsShowBiometricButton.set(false)
            }
        }
    }

    fun hasBiometricData() = biometricRequest?.natId != null


    fun saveBiometricData(){
        val request = BiometricRequest()
        request.natId = this.request.natId
        request.password = this.request.password
        app.sharedPrefs<BiometricRequest>(ConstString.PREF_USER_LOGIN_BIOMETRIC_DATA).setData(request)
    }

}