package com.gsgroup.hrapp.ui.fragment.news

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.gsgroup.hrapp.base.BaseParcelable
import com.gsgroup.hrapp.data.model.BaseObjectResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsResponse(

	@field:SerializedName("response")
	val response: Response? = null,
) : Parcelable,BaseObjectResponse()

@Parcelize
data class NewsItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("company")
	val company: List<Int?>? = null,

	@field:SerializedName("id")
	val id: Int = 0,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("category")
	val category: List<Int?>? = null
) : Parcelable, BaseParcelable{
	override fun unique() = id
}

@Parcelize
data class Response(

	@field:SerializedName("data")
	val data: List<NewsItem?>? = null,
) : Parcelable
