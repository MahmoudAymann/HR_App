package com.gsgroup.hrapp.ui.fragment.map.share

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShareLocationRequest(
    @SerializedName("latitude")
    var lat: String = "0.0",
    @SerializedName("longitude")
    var lng: String = "0.0"
) : Parcelable