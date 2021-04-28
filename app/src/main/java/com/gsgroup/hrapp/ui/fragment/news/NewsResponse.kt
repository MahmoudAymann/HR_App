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
):Parcelable, BaseObjectResponse()
@Parcelize
data class Response(

	@field:SerializedName("data")
	val data: List<ItemNews?>? = null
):Parcelable
@Parcelize
data class ItemNews(

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: Int = 0,

	@field:SerializedName("source")
	val source: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("published_at")
	val publishedAt: String? = null
):Parcelable,BaseParcelable{
	override fun unique() = id
}
