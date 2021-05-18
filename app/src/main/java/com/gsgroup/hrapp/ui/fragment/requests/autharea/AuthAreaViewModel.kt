package com.gsgroup.hrapp.ui.fragment.requests.autharea

import android.app.Application
import androidx.databinding.ObservableField
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.AndroidBaseViewModel
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.data.model.AreasItem
import com.gsgroup.hrapp.data.model.CityItem
import com.gsgroup.hrapp.data.model.SearchItemInterface
import com.gsgroup.hrapp.util.Resource
import com.gsgroup.hrapp.util.requestNewCallDeferred
import timber.log.Timber

class AuthAreaViewModel(app: Application) : AndroidBaseViewModel(app) {


    val request = AuthAreaRequestRequest()

    var citiesList: Array<SearchItemInterface>? = arrayOf()
    var areasList: Array<SearchItemInterface>? = arrayOf()
    val obsCityName = ObservableField(app.getString(R.string.select_city))
    val obsAreaName = ObservableField(app.getString(R.string.select_area))


    init {
        requestNewCallDeferred({ getAllCitiesCallAsync() }) {
            postResult(Resource.success())
            it.response?.data?.let { list ->
                citiesList = list.toTypedArray()
            }
        }
    }

    private fun gotCity(city: CityItem?) {
        city?.let {
            obsCityName.set(it.name)
        }
    }

    fun gotResultFromBottomSheet(it: SearchItemInterface?) {
        when (it) {
            is CityItem -> gotCity(it)
            is AreasItem -> gotArea(it)
            else -> Timber.e("error type")
        }
    }

    private fun gotArea(area: AreasItem?) {
        area?.let {
            obsAreaName.set(it.name)
            request.areaId = it.id
        }
    }

    fun onCityClick() {
        setValue(Codes.OPEN_CITY_LIST)
    }

    fun onAreaClick() {
        setValue(Codes.OPEN_AREA_LIST)
    }

    fun onSubmitClick() {
        if (request.isValid()) {
            requestNewCallDeferred({ authAreaRequestCallAsync() }) {
                postResult(Resource.success(msg = it.message))
            }
        } else {
            postResult(Resource.message(app.getString(R.string.all_data_required)))
        }
    }

    private fun authAreaRequestCallAsync() = apiHelper.authAreaRequestAsync(request)

    private fun getAllCitiesCallAsync() = apiHelper.getAllCitiesAsync()


}