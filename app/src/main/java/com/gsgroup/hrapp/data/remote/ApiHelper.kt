package com.gsgroup.hrapp.data.remote

import com.gsgroup.hrapp.data.model.AllCitiesAreaResponse
import com.gsgroup.hrapp.data.model.BaseObjectResponse
import com.gsgroup.hrapp.ui.fragment.changepassword.ChangePasswordRequest
import com.gsgroup.hrapp.ui.fragment.login.LoginRequest
import com.gsgroup.hrapp.ui.fragment.map.AttendanceRequest
import com.gsgroup.hrapp.ui.fragment.map.share.ShareLocationRequest
import com.gsgroup.hrapp.ui.fragment.requests.autharea.AuthAreaRequestRequest
import com.gsgroup.hrapp.ui.fragment.requests.medical_card.MedicalCardRequestRequest
import com.gsgroup.hrapp.ui.fragment.requests.phoneissue.PhoneIssueRequestRequest
import com.gsgroup.hrapp.ui.fragment.requests.salaryinfo.SalaryInfoRequestRequest
import com.gsgroup.hrapp.ui.fragment.sign_in_out_logs.AttendanceLogRequest
import kotlinx.coroutines.Deferred
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Part


/**
 * Created by MahmoudAyman on 7/28/2020.
 **/

class ApiHelper(private val apiService: ApiService) : ApiService {
    override  fun loginAsync(request: LoginRequest) =
        apiService.loginAsync(request)

    override fun getNewsDetailsAsync(id: Int) = apiService.getNewsDetailsAsync(id)

    override fun getNewsAsync() = apiService.getNewsAsync()
    override fun getFaqsAsync() = apiService.getFaqsAsync()

    override fun getPoliciesAsync() = apiService.getPoliciesAsync()
    override fun getDirectHrAsync() = apiService.getDirectHrAsync()

    override fun getDirectManagerAsync() = apiService.getDirectManagerAsync()

    override fun changePasswordAsync(request: ChangePasswordRequest) =
        apiService.changePasswordAsync(request)

    override fun checkInAsync(request: AttendanceRequest) = apiService.checkInAsync(request)

    override fun checkOutAsync(request: AttendanceRequest) = apiService.checkOutAsync(request)
    override fun shareLocationAsync(request: ShareLocationRequest) =
        apiService.shareLocationAsync(request)

    override fun complainRequestAsync(
        @Part("details") details: RequestBody?,
        @Part("is_urgent") isUrgent: RequestBody?,
        @Part("type") type: RequestBody?,
        @Part image: MultipartBody.Part?
    ) = apiService.complainRequestAsync(
        details,
        isUrgent,
        type,
        image
    )

    override fun printInsuranceRequestAsync() = apiService.printInsuranceRequestAsync()

    override fun authAreaRequestAsync(request: AuthAreaRequestRequest) =
        apiService.authAreaRequestAsync(request)

    override fun expCertificateRequestAsync() = apiService.expCertificateRequestAsync()

    override fun phoneNumberRequestAsync(request: PhoneIssueRequestRequest) =
        apiService.phoneNumberRequestAsync(request)

    override fun salaryInfoRequestAsync(request: SalaryInfoRequestRequest) = apiService.salaryInfoRequestAsync(request)

    override fun getAttendanceLogsAsync(date: String) = apiService.getAttendanceLogsAsync(date)


    override fun medicalCardRequestAsync(request: MedicalCardRequestRequest) = apiService.medicalCardRequestAsync(request)


    override fun getAllCitiesAsync() = apiService.getAllCitiesAsync()
}