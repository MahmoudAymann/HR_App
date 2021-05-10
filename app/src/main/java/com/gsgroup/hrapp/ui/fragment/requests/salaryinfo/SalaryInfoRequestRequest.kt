package com.gsgroup.hrapp.ui.fragment.requests.salaryinfo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.gsgroup.hrapp.base.BaseParcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SalaryInfoRequestRequest(
    @SerializedName("forwarded_to")
    var to:String?=null
) : Parcelable