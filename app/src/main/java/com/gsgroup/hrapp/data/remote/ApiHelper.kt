package com.gsgroup.hrapp.data.remote

import com.gsgroup.hrapp.data.remote.ApiService
import com.gsgroup.hrapp.ui.fragment.login.LoginRequest
import com.gsgroup.hrapp.ui.fragment.login.LoginResponse
import com.mabaat.androidapp.base.network.response.NetworkResponse


/**
 * Created by MahmoudAyman on 7/28/2020.
 **/

class ApiHelper(private val apiService: ApiService) : ApiService {
    override suspend fun login(request: LoginRequest): NetworkResponse<LoginResponse, ErrorResponse> = apiService.login(request)

}