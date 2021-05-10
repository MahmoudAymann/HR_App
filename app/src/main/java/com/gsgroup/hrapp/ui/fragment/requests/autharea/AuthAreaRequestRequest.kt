package com.gsgroup.hrapp.ui.fragment.requests.autharea

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AuthAreaRequestRequest(
    @SerializedName("details")
    var details: String? = null,
    @SerializedName("area_id")
    var areaId: Int = 0,
) : Parcelable {
    fun isValid(): Boolean {
        return !details.isNullOrBlank() && areaId != 0
    }
}