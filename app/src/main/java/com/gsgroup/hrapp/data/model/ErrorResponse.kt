package com.gsgroup.hrapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ErrorResponse(
	@field:SerializedName("message")
	val message: String? = null,
	@field:SerializedName("errors")
	val errors: List<String?>? = null,
	@field:SerializedName("status")
	val status: Int? = null
) : Parcelable
