package com.gsgroup.hrapp.util

import android.content.Context
import android.content.SharedPreferences
import com.gsgroup.hrapp.constants.ConstString


/**
 * Created by MahmoudAyman on 7/16/2020.
 **/
object SharedPrefUtil {
    private var PRIVATE_MODE = 0
    private fun Context.getPrefs(): SharedPreferences {
        return getSharedPreferences(ConstString.Const_PREFS_NAME, PRIVATE_MODE)
    }

    fun Context.getPrefLanguage(): String {
        return getPrefs().getString(
            ConstString.PREF_LANG,
            ConstString.DEFAULT_LANG
        ) ?: ConstString.DEFAULT_LANG
    }

    fun Context.setPrefLanguage(value: String) {
        getPrefs().edit().putString(ConstString.PREF_LANG, value).apply()
    }


    fun Context.getPrefString(key: String, defaultValue: String? = "n/a"): String {
        return getPrefs().getString(key, defaultValue)!!
    }

    fun Context.setPrefString(key: String, value: String) {
        getPrefs().edit().putString(key, value).apply()
    }

}