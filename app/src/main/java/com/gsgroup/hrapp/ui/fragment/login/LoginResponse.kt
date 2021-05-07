package com.gsgroup.hrapp.ui.fragment.login

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.gsgroup.hrapp.data.model.SearchItemInterface
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginResponse(

	@field:SerializedName("response")
	val response: Response? = null,
) : Parcelable

@Parcelize
data class Response(
	@field:SerializedName("data")
	val dataUser: DataUser? = null,
) : Parcelable

@Parcelize
data class AreasItem(

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("latitude")
	val latitude: Double = 0.0,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int = 0,

	@field:SerializedName("radius")
	val radius: String = "0.0",

	@field:SerializedName("longitude")
	val longitude: Double = 0.0
) : Parcelable, SearchItemInterface {
	override fun id() = id

	override fun name() = name
}

@Parcelize
data class Company(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
) : Parcelable

@Parcelize
data class City(
	@field:SerializedName("id")
	val id: Int = 0,
	@field:SerializedName("name")
	val name: String? = null,
	@field:SerializedName("areas")
	val areas: List<AreasItem>? = null,

	) : SearchItemInterface, Parcelable {
	override fun id() = id
	override fun name() = name
}

@Parcelize
data class Attendance(

	@field:SerializedName("area")
	val area: String? = null,

	@field:SerializedName("time_in")
	val timeIn: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("day")
	val day: String? = null,

	@field:SerializedName("time_out")
	val timeOut: String? = null
) : Parcelable

@Parcelize
data class DataUser(

	@field:SerializedName("image")
	val image: String? = null,
	@field:SerializedName("serial_mobile")
	val serial: String? = null,
	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("jobtitle")
	val jobtitle: String? = null,

	@field:SerializedName("cities")
	val citities: List<City>? = null,

	@field:SerializedName("token")
	val token: String = "",

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("company")
	val company: Company? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("department")
	val department: String? = null,

	@field:SerializedName("hasFulldocument")
	val hasFulldocument: Boolean? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("attendance")
	var attendance: Attendance? = null
) : Parcelable
