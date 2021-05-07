package com.gsgroup.hrapp.data.remote

import com.gsgroup.hrapp.data.model.BaseObjectResponse
import com.gsgroup.hrapp.data.model.DirectManagerOrHrResponse
import com.gsgroup.hrapp.data.model.ErrorResponse
import com.gsgroup.hrapp.ui.fragment.changepassword.ChangePasswordRequest
import com.gsgroup.hrapp.ui.fragment.company_polices.PolicesResponse
import com.gsgroup.hrapp.ui.fragment.faq.FAQsResponse
import com.gsgroup.hrapp.ui.fragment.login.LoginRequest
import com.gsgroup.hrapp.ui.fragment.login.LoginResponse
import com.gsgroup.hrapp.ui.fragment.map.AttendanceRequest
import com.gsgroup.hrapp.ui.fragment.news.NewsResponse
import com.gsgroup.hrapp.ui.fragment.news.details.NewsDetailsResponse
import com.gsgroup.hrapp.base.network.response.NetworkResponse
import com.gsgroup.hrapp.ui.fragment.map.AttendanceResponse
import com.gsgroup.hrapp.ui.fragment.map.share.ShareLocationRequest
import com.gsgroup.hrapp.ui.fragment.requests.complain.ComplainRequest
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Created by MahmoudAyman on 6/24/2020.
 **/

interface ApiService {
    companion object {
        const val keyMobile = "mobile"
    }

    @POST("$keyMobile/login")
     fun loginAsync(@Body request: LoginRequest): Deferred<LoginResponse>

    @GET("$keyMobile/news/{id}")
     fun getNewsDetailsAsync(@Path("id") id: Int): Deferred<NewsDetailsResponse>

    @GET("$keyMobile/news")
     fun getNewsAsync(): Deferred<NewsResponse>

    @GET("$keyMobile/faqs")
     fun getFaqsAsync(): Deferred<FAQsResponse>

    @GET("$keyMobile/policies")
     fun getPoliciesAsync(): Deferred<PolicesResponse>

    @GET("$keyMobile/direct_hr")
     fun getDirectHrAsync(): Deferred<DirectManagerOrHrResponse>

    @GET("$keyMobile/direct_manager")
     fun getDirectManagerAsync(): Deferred<DirectManagerOrHrResponse>


    @POST("$keyMobile/change_password")
     fun changePasswordAsync(@Body request: ChangePasswordRequest): Deferred<BaseObjectResponse>


    @POST("$keyMobile/check_in")
     fun checkInAsync(@Body request: AttendanceRequest): Deferred<AttendanceResponse>

    @POST("$keyMobile/check_out")
     fun checkOutAsync(@Body request: AttendanceRequest): Deferred<AttendanceResponse>

     @POST("$keyMobile/share_location")
     fun shareLocationAsync(@Body request: ShareLocationRequest): Deferred<AttendanceResponse>


     //requests
    @POST("$keyMobile/Complains")
    fun complainAsync(@Body request: ComplainRequest): Deferred<BaseObjectResponse>


}