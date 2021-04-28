package com.gsgroup.hrapp.data.remote

import com.gsgroup.hrapp.ui.fragment.map.AttendanceRequest
import com.gsgroup.hrapp.ui.fragment.changepassword.ChangePasswordRequest
import com.gsgroup.hrapp.ui.fragment.login.LoginRequest


/**
 * Created by MahmoudAyman on 7/28/2020.
 **/

class ApiHelper(private val apiService: ApiService) : ApiService {
    override  fun loginAsync(request: LoginRequest) =
        apiService.loginAsync(request)

    override  fun getNewsDetailsAsync(id: Int) = apiService.getNewsDetailsAsync(id)

    override  fun getNewsAsync() = apiService.getNewsAsync()
    override  fun getFaqsAsync() = apiService.getFaqsAsync()

    override  fun getPoliciesAsync()  = apiService.getPoliciesAsync()
    override  fun getDirectHrAsync() = apiService.getDirectHrAsync()

    override  fun getDirectManagerAsync() = apiService.getDirectManagerAsync()

    override  fun changePasswordAsync(request: ChangePasswordRequest) = apiService.changePasswordAsync(request)
    override  fun checkInAsync(request: AttendanceRequest) = apiService.checkInAsync(request)

    override  fun checkOutAsync() = apiService.checkOutAsync()
}