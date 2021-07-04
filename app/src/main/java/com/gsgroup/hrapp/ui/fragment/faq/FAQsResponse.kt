package com.gsgroup.hrapp.ui.fragment.faq

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.gsgroup.hrapp.base.BaseParcelable
import com.gsgroup.hrapp.data.model.BaseObjectResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class FAQsResponse(

	@field:SerializedName("response")
	val response: Response? = null,
) : Parcelable, BaseObjectResponse()

@Parcelize
data class FAQItem(

	@field:SerializedName("question")
	val question: String? = null,

	@field:SerializedName("answer")
	val answer: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
) : Parcelable, BaseParcelable{
	override fun unique() = id ?: 0
}

@Parcelize
data class Response(
	@field:SerializedName("data")
	val data: List<FAQItem?>? = null
) : Parcelable
