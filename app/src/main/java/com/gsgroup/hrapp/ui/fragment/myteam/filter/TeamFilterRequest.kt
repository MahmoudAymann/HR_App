package com.gsgroup.hrapp.ui.fragment.myteam.filter

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TeamFilterRequest(
    var name: String? = null,
    var cityId: Int? = null,
    var areaId: Int? = null,
    var jobTitleId: Int? = null
) : Parcelable