package com.gsgroup.hrapp.ui.fragment.changepassword

import com.google.gson.annotations.SerializedName

data class ChangePasswordRequest(
    @SerializedName("_method")
    var method: String = "PUT",
    @SerializedName("password")
    var password: String? = null,
    @SerializedName("new_password")
    var new_password: String? = null,
    @SerializedName("c_password")
    var c_password: String? = null,
) {


    fun isValid(): Boolean {
        return !password.isNullOrBlank() && !new_password.isNullOrBlank() && !c_password.isNullOrBlank() && new_password.equals(c_password)
    }


}