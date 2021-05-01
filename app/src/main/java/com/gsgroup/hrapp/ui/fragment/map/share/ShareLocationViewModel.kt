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
import java.text.SimpleDateFormat
import java.util.*

class ShareLocationViewModel(app: Application) : AndroidBaseViewModel(app) {

    val obsLatLng = ObservableField<LatLng>()
    val obsSerial = ObservableField<String>()
    val obsDateTime = ObservableField<String>()
    val isGranted = ObservableBoolean()
    fun gotData(args: ShareLocationFragmentArgs) {
        obsLatLng.set(LatLng(args.lat.toDouble(), args.lng.toDouble()))
        val serial = AppUtil.getDeviceSerial(app.applicationContext)
        obsSerial.set(serial)
        val sdf = SimpleDateFormat("dd/MM/yyyy hh:mm aa", Locale.ENGLISH)
        obsDateTime.set(sdf.format(Calendar.getInstance().time))


        if (PermissionUtil.hasAllPhoneCriticalPermissions(app.applicationContext)) {
            isGranted.set(true)
        }
    }

    fun onAllowPermissionClick() {
        setValue(Codes.ALLOW_PERMISSION)
    }


    fun onCancelClick(){
        setValue(Codes.BACK_BUTTON_PRESSED)
    }

    fun onSubmitClick(){

    }


    fun permissionResult(it: PermissionUtil.AppPermissionResult) {
        when (it) {
            ALL_GOOD -> isGranted.set(true)
            OPEN_SETTING -> {
                Codes.OPEN_PERMISSION_SETTING
                isGranted.set(false)
            }
            else -> isGranted.set(false)
        }
    }

}