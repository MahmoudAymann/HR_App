package com.gsgroup.hrapp.data.remote

import com.gsgroup.hrapp.ui.fragment.login.LoginRequest
import com.gsgroup.hrapp.ui.fragment.login.LoginResponse
import com.mabaat.androidapp.base.network.response.NetworkResponse
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by MahmoudAyman on 6/24/2020.
 **/

interface ApiService {

    @POST("mobile/login")
    suspend fun login(@Body request: LoginRequest): NetworkResponse<LoginResponse, ErrorResponse>

}