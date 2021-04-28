package com.gsgroup.hrapp.base

import android.app.Application
import androidx.annotation.StringRes
import androidx.databinding.Observable
import androidx.databinding.ObservableBoolean
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.gsgroup.hrapp.base.network.RetrofitBuilder
import com.gsgroup.hrapp.constants.ConstString
import com.gsgroup.hrapp.data.remote.ApiHelper
import com.gsgroup.hrapp.ui.fragment.login.DataUser
import com.gsgroup.hrapp.util.Resource
import com.gsgroup.hrapp.util.SharedPrefUtil.sharedPrefs

open class AndroidBaseViewModel(val app: Application) : AndroidViewModel(app), Observable {
    private val mCallBacks: PropertyChangeRegistry = PropertyChangeRegistry()
    val mutableLiveData = MutableLiveData<Any?>()
    var isLoading = ObservableBoolean()
    val userData by app.sharedPrefs<DataUser>(ConstString.PREF_USER_DATA)
    val apiHelper = ApiHelper(RetrofitBuilder(app).apiService)
    //for network
    val resultLiveData = MutableLiveData<Resource<Any?>?>()
    override fun onCleared() {
        super.onCleared()
    }


    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        mCallBacks.remove(callback)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        mCallBacks.add(callback)
    }

    fun notifyChange() {
        mCallBacks.notifyChange(this, 0)
    }

    fun notifyChange(propertyId: Int) {
        mCallBacks.notifyChange(this, propertyId)
    }

    fun setValue(o: Any?) {
        mutableLiveData.value = o
    }

    fun postValue(o: Any?) {
        mutableLiveData.postValue(o)
    }

    fun setResult(o: Resource<Any?>?) {
        resultLiveData.value = o
    }

    fun postResult(o: Resource<Any?>?) {
        resultLiveData.postValue(o)
    }

    fun getString(@StringRes stringRes: Int): String {
        return app.getString(stringRes)
    }

}