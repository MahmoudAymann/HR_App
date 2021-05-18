package com.gsgroup.hrapp.ui.fragment.myteam.filter


import android.app.Application
import androidx.databinding.ObservableField
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.AndroidBaseViewModel
import com.gsgroup.hrapp.data.model.AreasItem
import com.gsgroup.hrapp.data.model.CityItem
import com.gsgroup.hrapp.data.model.SearchItemInterface
import timber.log.Timber

class TeamFilterViewModel(app: Application) : AndroidBaseViewModel(app) {

    val obsCityName = ObservableField(app.getString(R.string.select_city))
    val obsAreaName = ObservableField(app.getString(R.string.select_area))
    val obsJobTitleName = ObservableField(app.getString(R.string.select_job_title))
    val citiesList: Array<SearchItemInterface>? = arrayOf()
    var areasList: Array<SearchItemInterface>? = arrayOf()
    var jobTitlesList: Array<SearchItemInterface>? = arrayOf()

    val request = TeamFilterRequest()


    init {


    }

    fun onSelectClick(code: Int) {
        setValue(code)

    }

    fun gotResultFromBottomSheet(it: SearchItemInterface?) {
        when (it) {
            is CityItem -> gotCity(it)
            is AreasItem -> gotArea(it)
            else -> Timber.e("error type")
        }
    }


    private fun gotArea(it: AreasItem?) {

    }

    private fun gotCity(it: CityItem?) {
        it?.let {
            obsCityName.set(it.name)
            areasList = it.areas?.toTypedArray()
        }
    }


    fun onSubmitClick() {

    }


}