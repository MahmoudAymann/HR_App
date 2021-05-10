package com.gsgroup.hrapp.ui.fragment.requests.complain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.io.File

@Parcelize
data class ComplainRequest(
    @SerializedName("details")
    var details: String? = null,
    @SerializedName("is_urgent")
    var isUrgent: Int = 0,
    var imageFile: File? = null,
    var type: String = HR_REQUEST,
) : Parcelable {

    fun isValid(): Boolean {
        return !details.isNullOrBlank()
    }

    companion object {
        const val HR_REQUEST = "hrRequest"
        const val DM_REQUEST = "directManagerRequest"
    }
}