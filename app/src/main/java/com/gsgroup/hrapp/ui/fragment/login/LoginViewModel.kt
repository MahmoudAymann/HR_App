package com.gsgroup.hrapp.ui.fragment.login

import android.app.Application
import androidx.databinding.ObservableBoolean
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.AndroidBaseViewModel
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.constants.ConstString
import com.gsgroup.hrapp.util.AppUtil
import com.gsgroup.hrapp.util.BiometricUtils
import com.gsgroup.hrapp.util.Resource
import com.gsgroup.hrapp.util.SharedPrefUtil.getBooleanPrefs
import com.gsgroup.hrapp.util.SharedPrefUtil.getPrefLanguage
import com.gsgroup.hrapp.util.SharedPrefUtil.getPrefs
import com.gsgroup.hrapp.util.SharedPrefUtil.setPrefLanguage
import com.gsgroup.hrapp.util.SharedPrefUtil.setPrefs
import com.gsgroup.hrapp.util.requestNewCallDeferred
import timber.log.Timber

class LoginViewModel(app: Application) : AndroidBaseViewModel(app) {
    val obsIsArabic = ObservableBoolean(app.getPrefLanguage() == ConstString.LANG_AR)
    private var selectedLang = ""
    val request = LoginRequest()
    val obsIsRememberMe = ObservableBoolean()
    val obsShowBiometricButton = ObservableBoolean()
    var biometricIsAvail = false

    private val rememberMeRequest: RememberMeRequest? by lazy {
        app.getPrefs(
            ConstString.PREF_USER_LOGIN_REMEMBER_ME_DATA
        )
    }

    private val biometricRequest: BiometricRequest? by lazy {
        app.getPrefs(
            ConstString.PREF_USER_LOGIN_BIOMETRIC_DATA
        )
    }

    private val dontAskForBiometric by lazy {
        app.getBooleanPrefs(ConstString.PREF_DONT_ASK_AGAIN_BIO, false)
    }


    fun onLangClick(isArabic: Boolean) {
        selectedLang = if (isArabic) ConstString.LANG_AR else ConstString.LANG_EN
        if (app.getPrefLanguage() != selectedLang) {
            setValue(Codes.CHANGE_LANG)
        }
    }

    fun onBiometricClick() {
        setValue(Codes.LOGIN_WITH_BIOMETRIC)
    }

    fun loginWithBiometricData() {
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

        request.natId = "11111222225555"
        request.password = "12345678"
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
            if (hasBiometricData()) {
                postValue(Codes.HOME_SCREEN)
            } else {
                if (AppUtil.isOldDevice()) {
                    postValue(Codes.HOME_SCREEN)
                } else {
                    if (biometricIsAvail && !dontAskForBiometric)
                        postValue(Codes.SHOW_BIOMETRIC_ASK_DIALOG)
                    else {
                        postValue(Codes.HOME_SCREEN)
                    }
                }
            }
        }
    }

    private fun saveUserDataInPrefs(data: DataUser?) {
        app.setPrefs<DataUser?>(ConstString.PREF_USER_DATA, data)
        val data = app.getPrefs<DataUser?>(ConstString.PREF_USER_DATA)
        Timber.e(" hii $data")
        if (obsIsRememberMe.get()) {
            rememberMeRequest?.natId = request.natId
            rememberMeRequest?.password = request.password
            app.setPrefs<RememberMeRequest?>(
                ConstString.PREF_USER_LOGIN_REMEMBER_ME_DATA,
                rememberMeRequest
            )
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
                biometricIsAvail = true
                if (hasBiometricData())
                    obsShowBiometricButton.set(true)
                else
                    obsShowBiometricButton.set(false)
            }
            else -> {
                obsShowBiometricButton.set(false)
                biometricIsAvail = false
            }
        }
    }

    private fun hasBiometricData() = biometricRequest?.natId != null


    fun saveBiometricData() {
        val request = BiometricRequest()
        request.natId = this.request.natId
        request.password = this.request.password
        app.setPrefs(ConstString.PREF_USER_LOGIN_BIOMETRIC_DATA, request)
    }

}