package com.gsgroup.hrapp.ui.fragment.map

import android.app.Application
import android.location.Location
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Circle
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.AndroidBaseViewModel
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.constants.ConstString
import com.gsgroup.hrapp.data.model.AreasItem
import com.gsgroup.hrapp.data.model.CityItem
import com.gsgroup.hrapp.data.model.SearchItemInterface
import com.gsgroup.hrapp.ui.fragment.login.Attendance
import com.gsgroup.hrapp.ui.fragment.login.DataUser
import com.gsgroup.hrapp.util.*
import com.gsgroup.hrapp.util.MapUtil.animateCameraOnMarkers
import com.gsgroup.hrapp.util.MapUtil.animateCameraToPosition
import com.gsgroup.hrapp.util.MapUtil.drawRadiusAroundMarker
import com.gsgroup.hrapp.util.MapUtil.getAreaMarker
import com.gsgroup.hrapp.util.MapUtil.getCurrentMarker
import com.gsgroup.hrapp.util.SharedPrefUtil.setPrefs
import timber.log.Timber

class MapViewModel(app: Application) : AndroidBaseViewModel(app) {

    private var circle: Circle? = null
    private var mMap: GoogleMap? = null
    val request = AttendanceRequest()
    val obsCityName = ObservableField(app.getString(R.string.select_city))
    val obsAreaName = ObservableField(app.getString(R.string.select_area))
    val obsIsCheckIn = ObservableBoolean()
    var isCitySelected = false
    val citiesList: Array<SearchItemInterface>? = userData?.citities?.toTypedArray()
    var areasList: Array<SearchItemInterface>? = arrayOf()
    private var currentMarker: Marker? = null
    private var areaMarker: Marker? = null
    private val serial = AppUtil.getDeviceSerial(app.applicationContext)

    init {
        isLoading.set(true)
    }

    fun gotArgs(city: MapFragmentArgs) {
        obsIsCheckIn.set(city.isCheckIn)
    }

    fun onCurrentLocationClick() {
        request.currentLatLng?.let {
            mMap?.animateCameraToPosition(it, false)
        }
    }

    fun onFocusDirectionClick() {
        if (!request.currentLatLng.isNull() && !request.areaLatLng.isNull()) {
            mMap?.let {
                if (!currentMarker.isNull() && !areaMarker.isNull())
                    it.animateCameraOnMarkers(arrayListOf(currentMarker!!, areaMarker!!))
            }
        }
    }

    fun onShareLocationClick() {
        setValue(Codes.SHARE_LOCATION_CLICK)
    }

    fun onLoginClick() {
        if (request.isValid()) {
            if (isInArea()) {
                request.serial = serial
                requestNewCallDeferred({ if (obsIsCheckIn.get()) checkInCallAsync() else checkOutCallAsync() }) {
                    updateUserDataPrefs(it.response?.data)
                    postResult(Resource.success(msg = it.message))
                }
            }
        } else {
            postResult(Resource.message(app.getString(R.string.invalid_area_selected)))
        }
    }

    private fun updateUserDataPrefs(attendance: Attendance?) {
        val newUserData = userData
        newUserData?.attendance = attendance
        app.setPrefs(ConstString.PREF_USER_DATA, newUserData)
        Timber.e("${newUserData?.attendance}")
        Timber.e("${userData}")
    }

    private fun checkInCallAsync() = apiHelper.checkInAsync(request)
    private fun checkOutCallAsync() = apiHelper.checkOutAsync(request)

    fun onCityClick() {
        setValue(Codes.OPEN_CITY_LIST)
    }

    fun onAreaClick() {
        setValue(Codes.OPEN_AREA_LIST)
    }

    fun onMapReady(gm: GoogleMap?) {
        gm?.let {
            mMap = it
        }
    }

    private fun isInArea(): Boolean {
        return when {
            request.currentLatLng.isNull() -> {
                postResult(Resource.message(app.getString(R.string.getting_your_location)))
                false
            }
            MapUtil.isInArea(
                request.currentLatLng!!,
                request.areaLatLng!!,
                request.areaRadius
            ) -> true
            else -> {
                postResult(Resource.message(app.getString(R.string.out_of_area_range)))
                false
            }
        }
    }

    fun gotResultFromBottomSheet(it: SearchItemInterface?) {
        when (it) {
            is CityItem -> gotCity(it)
            is AreasItem -> gotArea(it)
            else -> Timber.e("error type")
        }
    }


    private fun gotCity(city: CityItem?) {
        city?.let {
            obsCityName.set(it.name)
            areasList = it.areas?.toTypedArray()
            isCitySelected = true
        } ?: run { isCitySelected = false }
    }

    private fun gotArea(area: AreasItem?) {
        area?.let {
            obsAreaName.set(it.name)
            request.areaId = it.id
            request.areaRadius = it.radius.toDouble()
            request.areaLatLng = LatLng(it.latitude, it.longitude)
            setAreaMarker()
        }
    }

    fun gotCurrentLocation(location: Location?) {
        location?.let {
            request.currentLatLng = LatLng(location.latitude, location.longitude)
            detectNearbyArea()
            mMap?.let {
                setCurrentMarker()
            }
        }
        isLoading.set(false)
    }

    private fun detectNearbyArea() {

    }

    private fun setCurrentMarker() {
        val latLng = request.currentLatLng
        currentMarker?.apply {
            position = latLng
        } ?: run { currentMarker = mMap?.getCurrentMarker(latLng!!, true) }
    }

    private fun setAreaMarker() {
        val latLng = request.areaLatLng
        //for areaMarker
        areaMarker?.apply {
            position = latLng
        } ?: run {
            areaMarker = mMap?.getAreaMarker(latLng!!)
        }

        //for circle radius
        circle?.apply {
            center = latLng
        } ?: run {
            circle = areaMarker?.drawRadiusAroundMarker(mMap, request.areaRadius)
        }

    }


}