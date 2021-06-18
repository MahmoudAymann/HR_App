package com.gsgroup.hrapp.util

import android.content.Context
import android.content.SharedPreferences
import android.os.Parcelable
import androidx.core.content.edit
import com.google.gson.Gson
import com.gsgroup.hrapp.constants.ConstString
import kotlinx.coroutines.*
import timber.log.Timber
import java.lang.ClassCastException
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


/**
 * Created by MahmoudAyman on 7/16/2020.
 **/

object SharedPrefUtil {

    fun Context.getAppPrefs(): SharedPreferences =
        getSharedPreferences(ConstString.PREFS_FILE_NAME, Context.MODE_PRIVATE)

    fun Context.getPrefLanguage(): String {
        return getPrefs(ConstString.PREF_LANG, ConstString.DEFAULT_LANG)!!
    }

    fun Context.setPrefLanguage(value: String) {
        setPrefs(ConstString.PREF_LANG, value)
    }

    fun Context.getPrefFirebaseToken(): String {
        return getPrefs(ConstString.PREF_FIREBASE_TOKEN, "n/a")!!
    }

    fun Context.setPrefFirebaseToken(value: String?) {
        setPrefs(ConstString.PREF_FIREBASE_TOKEN, value)
    }


    fun <T> Context.setPrefs(key: String, value: T?) {
        getAppPrefs().edit {
            when (value) {
                is String -> putString(key, value).apply()
                is Int -> putInt(key, value).apply()
                is Boolean -> putBoolean(key, value).apply()
                is Float -> putFloat(key, value).apply()
                is Double -> putString(key, value.toString()).apply()
                is Parcelable -> {
                    val gson by lazy { Gson() }
                    Timber.tag("hii").e(value.toString())
                    putString(key, gson.toJson(value)).apply()
                }
            }
        }
    }

    inline fun <reified T> Context.getPrefs(key: String, defaultValue: T? = null): T? {
        val t: T? = (T::class.java).newInstance()
        getAppPrefs().apply {
            return when (t) {
                is String -> getString(key, defaultValue as String) as T
                is Int -> getInt(key, defaultValue as Int) as T
                is Float -> getFloat(key, defaultValue as Float) as T
                is Double -> getString(key, defaultValue as String) as T
                is Parcelable -> {
                    val gson by lazy { Gson() }
                    val jsonString = getString(key, null)
                    gson.fromJson(jsonString, T::class.java)
                }
                else -> throw ClassCastException("no such type for : $t")
            }
        }
    }

    fun Context.getBooleanPrefs(key: String, defaultValue: Boolean = false): Boolean {
        return getAppPrefs().getBoolean(key, defaultValue)
    }

    fun Context.deleteAllSharedPrefData() = getAppPrefs().edit().clear().apply()

    fun Context.deleteSharedPrefData(key: String) = getAppPrefs().edit().remove(key).apply()


}


