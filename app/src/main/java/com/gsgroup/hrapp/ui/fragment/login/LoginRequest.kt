package com.gsgroup.hrapp.ui.fragment.login

data class LoginRequest(var email: String? = null, var password: String? = null) {

    fun isValid(): Boolean {
        return !email.isNullOrEmpty() &&
               !password.isNullOrEmpty()
    }
}