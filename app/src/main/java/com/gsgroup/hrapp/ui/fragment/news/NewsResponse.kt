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
) : Parcelable, BaseObjectResponse()

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
data class Category(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
) : Parcelable

@Parcelize
data class NewsItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("company")
	val company: Company? = null,

	@field:SerializedName("id")
	val id: Int = 0,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("category")
	val category: Category? = null
) : Parcelable, BaseParcelable{
	override fun unique() = id
}

@Parcelize
data class Response(

	@field:SerializedName("data")
	val data: List<NewsItem?>? = null
) : Parcelable
