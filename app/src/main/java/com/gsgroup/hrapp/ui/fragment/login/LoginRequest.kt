package com.gsgroup.hrapp.ui.fragment.login

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("nationalId")
    var natId: String? = null,
    @SerializedName("password")
    var password: String? = null) {

    fun isValid(): Boolean {
        return !natId.isNullOrEmpty() &&
               !password.isNullOrEmpty()
    }
}