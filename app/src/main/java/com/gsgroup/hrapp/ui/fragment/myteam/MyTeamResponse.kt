package com.gsgroup.hrapp.ui.fragment.myteam

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.gsgroup.hrapp.base.BaseParcelable
import com.gsgroup.hrapp.data.model.AreasItem
import com.gsgroup.hrapp.data.model.BaseObjectResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class MyTeamResponse(
	@field:SerializedName("response")
	val response: Response? = null,
) : Parcelable, BaseObjectResponse()

@Parcelize
data class MyTeamItem(

	@field:SerializedName("area")
	val area: String? = null,

	@field:SerializedName("Company")
	val company: String? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("UserName")
	val userName: String? = null,

	@field:SerializedName("id")
	val id: Int = 0,

	@field:SerializedName("Code")
	val code: String? = null,

	@field:SerializedName("JobTitle")
	val jobTitle: String? = null
) : Parcelable, BaseParcelable{
	override fun unique() = id
}

@Parcelize
data class Response(

	@field:SerializedName("data")
	val data: List<MyTeamItem?>? = null,

	) : Parcelable
