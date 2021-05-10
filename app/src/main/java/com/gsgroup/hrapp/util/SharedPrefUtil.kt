package com.gsgroup.hrapp.util

import android.content.Context
import android.content.SharedPreferences
import android.os.Parcelable
import androidx.core.content.edit
import com.google.gson.Gson
import com.gsgroup.hrapp.constants.ConstString
import com.gsgroup.hrapp.ui.fragment.login.DataUser
import com.gsgroup.hrapp.util.MapUtil.isGPSEnabled
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


/**
 * Created by MahmoudAyman on 7/16/2020.
 **/
object SharedPrefUtil {


    fun Context.getAppPrefs(): SharedPreferences =
        getSharedPreferences(ConstString.Const_PREFS_NAME, Context.MODE_PRIVATE)

    fun Context.getPrefLanguage(): String {
        val language by sharedPrefs(ConstString.PREF_LANG, ConstString.DEFAULT_LANG)
        return language!!
    }

    fun Context.setPrefLanguage(value: String) {
        sharedPrefs<String>(ConstString.PREF_LANG).setData(value)
    }


    fun Context.getPrefFirebaseToken(): String {
        val token by sharedPrefs(ConstString.PREF_FIREBASE_TOKEN, "n/a")
        return token!!
    }

    fun Context.setPrefFirebaseToken(value: String) {
        sharedPrefs<String>(ConstString.PREF_LANG).setData(value)
    }

    fun <T> ReadWriteProperty<Any?, T>.setData(value: T) {
        setValue(this, KProperty<*>::name, value)
    }

    inline fun <reified T> getReadWrite(
        context: Context,
        key: String,
        defaultValue: T? = null
    ) = object : ReadWriteProperty<Any?, T?> {

        val sharedPrefs by lazy {
            context.getAppPrefs()
        }

        val gson by lazy { Gson() }
        var newData: T? = (T::class.java).newInstance()

        override fun getValue(thisRef: Any?, property: KProperty<*>): T? {
            return getPrefs()
        }

        override fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
            this.newData = value
            putPrefs(newData)
        }

        fun putPrefs(value: T?) {
            sharedPrefs.edit {
                when (value) {
                    is Int -> putInt(key, value)
                    is Boolean -> putBoolean(key, value)
                    is String -> putString(key, value)
                    is Long -> putLong(key, value)
                    is Float -> putFloat(key, value)
                    is Parcelable -> putString(key, gson.toJson(value))
                    else -> throw Throwable("no such type exist to put data")
                }
            }
        }

        fun getPrefs(): T? {
            return when (newData) {
                is Int -> sharedPrefs.getInt(key, defaultValue as Int) as T
                is Boolean -> sharedPrefs.getBoolean(key, false) as T
                is String -> sharedPrefs.getString(key, defaultValue as String) as T
                    ?: defaultValue as T
                is Long -> sharedPrefs.getLong(key, defaultValue as Long) as T
                is Float -> sharedPrefs.getFloat(key, defaultValue as Float) as T
                is Parcelable -> gson.fromJson(sharedPrefs.getString(key, null), T::class.java)
                else -> throw Throwable("no such type exist to put data")
            } ?: newData
        }
    }

    inline fun <reified T> Context.sharedPrefs(key: String, defaultValue: T? = null) =
        getReadWrite(this, key, defaultValue)


    fun Context.deleteAllSharedPrefData() = getAppPrefs().edit().clear().apply()

     fun  Context.deleteSharedPrefData(key: String) = getAppPrefs().edit().remove(key).apply()

}


