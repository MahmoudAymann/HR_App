package com.gsgroup.hrapp.ui.fragment.login

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("email")
    var email: String? = null,
    @SerializedName("password")
    var password: String? = null) {

    fun isValid(): Boolean {
        return !email.isNullOrEmpty() &&
               !password.isNullOrEmpty()
    }
}