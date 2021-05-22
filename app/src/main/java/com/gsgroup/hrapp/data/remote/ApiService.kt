package com.gsgroup.hrapp.data.remote

import com.gsgroup.hrapp.data.model.AllCitiesAreaResponse
import com.gsgroup.hrapp.data.model.BaseObjectResponse
import com.gsgroup.hrapp.data.model.DirectManagerOrHrResponse
import com.gsgroup.hrapp.ui.fragment.changepassword.ChangePasswordRequest
import com.gsgroup.hrapp.ui.fragment.company_polices.PolicesResponse
import com.gsgroup.hrapp.ui.fragment.faq.FAQsResponse
import com.gsgroup.hrapp.ui.fragment.login.LoginRequest
import com.gsgroup.hrapp.ui.fragment.login.LoginResponse
import com.gsgroup.hrapp.ui.fragment.map.AttendanceRequest
import com.gsgroup.hrapp.ui.fragment.map.AttendanceResponse
import com.gsgroup.hrapp.ui.fragment.map.share.ShareLocationRequest
import com.gsgroup.hrapp.ui.fragment.myteam.MyTeamResponse
import com.gsgroup.hrapp.ui.fragment.news.NewsResponse
import com.gsgroup.hrapp.ui.fragment.news.details.NewsDetailsResponse
import com.gsgroup.hrapp.ui.fragment.requests.autharea.AuthAreaRequestRequest
import com.gsgroup.hrapp.ui.fragment.requests.medical_card.MedicalCardRequestRequest
import com.gsgroup.hrapp.ui.fragment.requests.mission.MissionRequestRequest
import com.gsgroup.hrapp.ui.fragment.requests.permission.PermissionRequestRequest
import com.gsgroup.hrapp.ui.fragment.requests.phoneissue.PhoneIssueRequestRequest
import com.gsgroup.hrapp.ui.fragment.requests.salaryinfo.SalaryInfoRequestRequest
import kotlinx.coroutines.Deferred
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

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
    @Multipart
    @POST("$keyMobile/Complains")
    fun complainRequestAsync(@Part("details") details:RequestBody?,
                             @Part("is_urgent") isUrgent:RequestBody?,
                             @Part("type") type:RequestBody?,
                             @Part image: MultipartBody.Part?): Deferred<BaseObjectResponse>

    @POST("$keyMobile/hrRequest/insuranceCertificate")
    fun printInsuranceRequestAsync(): Deferred<BaseObjectResponse>

    @POST("$keyMobile/hrRequest/workAuthorization")
    fun authAreaRequestAsync(@Body request: AuthAreaRequestRequest): Deferred<BaseObjectResponse>


    @POST("$keyMobile/hrRequest/experienceCertificate")
    fun expCertificateRequestAsync(): Deferred<BaseObjectResponse>

    @POST("$keyMobile/hrRequest/problemInLine")
    fun phoneNumberRequestAsync(@Body request: PhoneIssueRequestRequest): Deferred<BaseObjectResponse>

    @POST("$keyMobile/hrRequest/extractSalarySlip")
    fun salaryInfoRequestAsync(@Body request: SalaryInfoRequestRequest): Deferred<BaseObjectResponse>


    @POST("$keyMobile/hrRequest/medicalCardRequest")
    fun medicalCardRequestAsync(@Body request: MedicalCardRequestRequest): Deferred<BaseObjectResponse>

    @POST("$keyMobile/directManagerRequest/permissionRequest")
    fun permissionRequestAsync(@Body request: PermissionRequestRequest): Deferred<BaseObjectResponse>

    @POST("$keyMobile/directManagerRequest/missionRequest")
    fun missionRequestAsync(@Body request: MissionRequestRequest): Deferred<BaseObjectResponse>

    @GET("$keyMobile/attendance_record")
    fun getAttendanceLogsAsync(
        @Query("day") date: String,
        @Query("user_id") userId: String?
    ): Deferred<AttendanceResponse>

    @GET("$keyMobile/staff")
    fun getMyTeamAsync(): Deferred<MyTeamResponse>

    @GET("$keyMobile/cities_with_areas")
    fun getAllCitiesAsync(): Deferred<AllCitiesAreaResponse>
}