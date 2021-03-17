package com.gsgroup.hrapp.ui.fragment.map

import androidx.databinding.ObservableBoolean
import com.gsgroup.hrapp.base.BaseViewModel
import com.gsgroup.hrapp.model.ListDataItem
import java.util.*
import kotlin.collections.ArrayList

class MapViewModel : BaseViewModel() {

    val obsShowAreas = ObservableBoolean()
    val citiesList = ListDataItem.getDummyData()

    fun onLoginClick(){

    }
}