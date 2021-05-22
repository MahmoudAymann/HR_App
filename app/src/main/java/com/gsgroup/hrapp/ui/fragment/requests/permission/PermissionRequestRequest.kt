package com.gsgroup.hrapp.ui.fragment.requests.permission

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PermissionRequestRequest(
    @SerializedName("type")
    var type: String? = null,
    @SerializedName("permission_day")
    var permissionDay: String? = null,
) : Parcelable {

    fun isValid(): Boolean {
        return !permissionDay.isNullOrBlank() && !type.isNullOrBlank()
    }


    companion object {
        const val TYPE_MORNING = "morning"
        const val TYPE_EVENING = "evening"
    }

}