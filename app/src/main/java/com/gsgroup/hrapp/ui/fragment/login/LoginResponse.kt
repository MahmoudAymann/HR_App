package com.gsgroup.hrapp.ui.fragment.login

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginResponse(

	@field:SerializedName("response")
	val response: Response? = null,

	@field:SerializedName("message")
	val message: String? = null,
	@field:SerializedName("status")
	val status: Int? = null
) : Parcelable

@Parcelize
data class Response(

	@field:SerializedName("data")
	val data: Data? = null,
) : Parcelable

@Parcelize
data class Data(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("token")
	val token: String? = null
) : Parcelable
