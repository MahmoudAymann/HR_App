package com.gsgroup.hrapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DirectManagerOrHrResponse(

	@field:SerializedName("response")
	val response: HRResponse? = null,
) : Parcelable, BaseObjectResponse()

@Parcelize
data class DirectData(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("Company")
	val company: String? = null,

	@field:SerializedName("UserName")
	val userName: String? = null,

	@field:SerializedName("Email")
	val email: String? = null,

	@field:SerializedName("PrivateMobile")
	val privateMobile: String? = null,

	@field:SerializedName("BussniessMobile")
	val bussniessMobile: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("JobTitle")
	val jobTitle: String? = null
) : Parcelable

@Parcelize
data class HRResponse(

	@field:SerializedName("data")
	val data: DirectData? = null
) : Parcelable
