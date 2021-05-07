package com.gsgroup.hrapp.ui.fragment.news.details

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.gsgroup.hrapp.data.model.BaseObjectResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsDetailsResponse(

	@field:SerializedName("response")
	val response: Response? = null,

) : Parcelable, BaseObjectResponse()

@Parcelize
data class Response(
	@field:SerializedName("data")
	val data: NewsData? = null
) : Parcelable

@Parcelize
data class NewsData(

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("published_at")
	val publishedAt: String? = null
) : Parcelable

