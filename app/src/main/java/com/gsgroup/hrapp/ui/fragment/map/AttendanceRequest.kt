package com.gsgroup.hrapp.ui.fragment.map

import com.google.android.gms.maps.model.LatLng
import com.google.gson.annotations.SerializedName
import com.gsgroup.hrapp.util.PermissionUtil
import java.security.Permission

data class AttendanceRequest(
    @SerializedName("area_id")
    var areaId: Int = 0,
    @SerializedName("serial_mobile")
    var serial:String?=null,
    var currentLatLng: LatLng? = null,
    var areaLatLng: LatLng? = null,
    var areaRadius: Double = 0.0
) {
    fun isValid(): Boolean {
        return areaId != 0
    }
}