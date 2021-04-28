package com.gsgroup.hrapp.ui.fragment.company_polices

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.gsgroup.hrapp.base.BaseParcelable
import com.gsgroup.hrapp.data.model.BaseObjectResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class PolicesResponse(

	@field:SerializedName("response")
	val response: Response? = null,

) : Parcelable,BaseObjectResponse()

@Parcelize
data class Response(

	@field:SerializedName("data")
	val data: List<CompanyPolicyItem?>? = null
) : Parcelable

@Parcelize
data class CompanyPolicyItem(

	@field:SerializedName("file")
	val file: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: Int = 0,

	@field:SerializedName("title")
	val title: String? = null
) : Parcelable,BaseParcelable{
	override fun unique() = id
}
