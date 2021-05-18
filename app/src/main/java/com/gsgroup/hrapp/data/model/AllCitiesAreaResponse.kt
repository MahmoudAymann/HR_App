package com.gsgroup.hrapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AllCitiesAreaResponse(

	@field:SerializedName("response")
	val response: Response? = null,
) : Parcelable, BaseObjectResponse()

@Parcelize
data class CityItem(
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
data class Response(
	@field:SerializedName("data")
	val data: List<CityItem>? = null
) : Parcelable
