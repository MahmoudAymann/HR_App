package com.gsgroup.hrapp.ui.fragment.requests.phoneissue

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhoneIssueRequestRequest(

    @SerializedName("phone")
    var phone: String? = null,
    @SerializedName("details")
    var details: String? = null
) : Parcelable