package com.gsgroup.hrapp.ui.fragment.sign_in_out_logs

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AttendanceLogRequest(
    var day: String? = null,
    var month: String? = null,
    var year: String? = null,
) : Parcelable