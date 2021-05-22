package com.gsgroup.hrapp.ui.fragment.requests.mission

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import timber.log.Timber

@Parcelize
data class MissionRequestRequest(
    @SerializedName("from_day")
    var fromDate: String? = null,
    @SerializedName("to_day")
    var toDate: String? = null,
    @SerializedName("from_time")
    var fromTime: String? = null,
    @SerializedName("to_time")
    var toTime: String? = null,
    @SerializedName("details")
    var details: String? = null,
    @SerializedName("is_full_time")
    var fullTime: Int = FULL_TIME_OFF,

    ) : Parcelable {
    fun isValid(): Boolean {
        return !fromDate.isNullOrBlank() && !toDate.isNullOrBlank() && !details.isNullOrBlank() && getIsTimeValid()
    }

    private fun getIsTimeValid(): Boolean {
        return if (fullTime == FULL_TIME_OFF) {
            Timber.e("")
            !fromTime.isNullOrBlank() && !toTime.isNullOrBlank()
        } else {
            true
        }
    }

    companion object {
        const val FULL_TIME_ON = 1
        const val FULL_TIME_OFF = 0
    }


}