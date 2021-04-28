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
import timber.log.Timber

class LoginViewModel(app: Application) : AndroidBaseViewModel(app) {
    val obsIsArabic = ObservableBoolean(app.getPrefLanguage() == ConstString.LANG_AR)
    private var selectedLang = ""
    val request = LoginRequest()

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
        request.email = "user@test.com"
        request.password = "12345678"
        if (request.isValid()) {
            requestNewCallDeferred({ loginCallAsync() }) {
                app.sharedPrefs<DataUser>(ConstString.PREF_USER_DATA)
                    .setData((it.response?.dataUser))
                Timber.e("$userData")
                postResult(Resource.success(it))
            }
        } else {
            postResult(Resource.message(app.getString(R.string.all_data_required)))
        }
    }

    private fun loginCallAsync() = apiHelper.loginAsync(request)

}