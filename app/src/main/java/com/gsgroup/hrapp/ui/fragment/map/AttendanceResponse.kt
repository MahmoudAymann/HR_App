package com.gsgroup.hrapp.ui.fragment.map

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.gsgroup.hrapp.data.model.BaseObjectResponse
import com.gsgroup.hrapp.ui.fragment.login.Attendance
import kotlinx.parcelize.Parcelize

@Parcelize
data class AttendanceResponse(

	@field:SerializedName("response")
	val response: Response? = null,

) : Parcelable, BaseObjectResponse()

@Parcelize
data class Response(

	@field:SerializedName("data")
	val data: Attendance? = null
) : Parcelable
