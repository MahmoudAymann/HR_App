package com.gsgroup.hrapp.ui.fragment.map

import androidx.databinding.ObservableBoolean
import com.gsgroup.hrapp.base.BaseViewModel
import com.gsgroup.hrapp.model.ListDataItem
import java.util.*

class MapViewModel : BaseViewModel() {

    val obsShowAreas = ObservableBoolean()
    val citiesList = arrayListOf(ListDataItem.getDummyData())

    fun onLoginClick(){

    }
}