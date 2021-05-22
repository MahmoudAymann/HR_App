package com.gsgroup.hrapp.ui.fragment.news.details

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.gsgroup.hrapp.data.model.BaseObjectResponse
import com.gsgroup.hrapp.ui.fragment.news.NewsItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsDetailsResponse(

	@field:SerializedName("response")
	val response: Response? = null,

) : Parcelable, BaseObjectResponse()

@Parcelize
data class Response(
	@field:SerializedName("data")
	val data: NewsItem? = null
) : Parcelable


