package com.gsgroup.hrapp.ui.fragment.map.share

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.google.android.gms.maps.model.LatLng
import com.gsgroup.hrapp.base.AndroidBaseViewModel
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.util.AppUtil
import com.gsgroup.hrapp.util.PermissionUtil
import com.gsgroup.hrapp.util.PermissionUtil.AppPermissionResult.ALL_GOOD
import com.gsgroup.hrapp.util.PermissionUtil.AppPermissionResult.OPEN_SETTING
import com.gsgroup.hrapp.util.Resource
import com.gsgroup.hrapp.util.requestNewCallDeferred
import java.text.SimpleDateFormat
import java.util.*

class ShareLocationViewModel(app: Application) : AndroidBaseViewModel(app) {

    val obsLatLng = ObservableField<LatLng>()
    val obsSerial = ObservableField<String>()
    val obsDateTime = ObservableField<String>()
    val request = ShareLocationRequest()
    fun gotData(args: ShareLocationFragmentArgs) {
        val lat = args.lat
        val lng = args.lng
        obsLatLng.set(LatLng(lat.toDouble(), lng.toDouble()))
        request.lat = lat
        request.lng = lng
        val serial = AppUtil.getDeviceSerial(app.applicationContext)
        obsSerial.set(serial)


        val sdf = SimpleDateFormat("dd/MM/yyyy hh:mm aa", Locale.ENGLISH)
        obsDateTime.set(sdf.format(Calendar.getInstance().time))
    }

    fun onAllowPermissionClick() {
        setValue(Codes.ALLOW_PERMISSION)
    }


    fun onCancelClick(){
        setValue(Codes.BACK_BUTTON_PRESSED)
    }

    fun onSubmitClick(){
        requestNewCallDeferred({ shareLocationCallAsync() }) {
            postResult(Resource.success(it))
        }
    }
    private fun shareLocationCallAsync() = apiHelper.shareLocationAsync(request)

}