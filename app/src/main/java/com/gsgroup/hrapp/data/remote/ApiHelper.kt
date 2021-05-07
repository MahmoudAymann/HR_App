package com.gsgroup.hrapp.data.remote

import com.gsgroup.hrapp.data.model.BaseObjectResponse
import com.gsgroup.hrapp.ui.fragment.map.AttendanceRequest
import com.gsgroup.hrapp.ui.fragment.changepassword.ChangePasswordRequest
import com.gsgroup.hrapp.ui.fragment.login.LoginRequest
import com.gsgroup.hrapp.ui.fragment.map.AttendanceResponse
import com.gsgroup.hrapp.ui.fragment.map.share.ShareLocationRequest
import com.gsgroup.hrapp.ui.fragment.requests.complain.ComplainRequest
import kotlinx.coroutines.Deferred


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

    override  fun checkOutAsync(request: AttendanceRequest) = apiService.checkOutAsync(request)
    override fun shareLocationAsync(request: ShareLocationRequest) = apiService.shareLocationAsync(request)

    override fun complainAsync(request: ComplainRequest) = apiService.complainAsync(request)


}