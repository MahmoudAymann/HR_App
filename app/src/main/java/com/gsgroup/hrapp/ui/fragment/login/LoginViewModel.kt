package com.gsgroup.hrapp.ui.fragment.login

import android.app.Application
import androidx.databinding.ObservableBoolean
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.AndroidBaseViewModel
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.constants.ConstString
import com.gsgroup.hrapp.data.remote.ApiHelper
import com.gsgroup.hrapp.util.Resource
import com.gsgroup.hrapp.util.SharedPrefUtil.getPrefLanguage
import com.gsgroup.hrapp.util.SharedPrefUtil.setPrefLanguage
import com.gsgroup.hrapp.util.requestNewCallRefactor
import com.gsgroup.hrapp.base.network.RetrofitBuilder

class LoginViewModel( app: Application) : AndroidBaseViewModel(app) {

    val obsIsArabic = ObservableBoolean(app.getPrefLanguage() == ConstString.LANG_AR)
    private var selectedLang = ""
    val request = LoginRequest()
    private val apiHelper = ApiHelper(RetrofitBuilder.API_SERVICE)

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

    fun onLoginClick() {
//        if (request.isValid()) {
//            requestNewCallRefactor({ loginCall() }) {
                postResult(Resource.success(null, null))
//            }
//        } else {
//            postResult(Resource.message(app.getString(R.string.all_data_required)))
//        }
    }

    private suspend fun loginCall() = apiHelper.login(request)

}