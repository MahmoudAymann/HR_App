package com.gsgroup.hrapp.ui.fragment.requests.types

import android.content.Context
import android.os.Parcelable
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseParcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemRequestType(val id: RequestTypes, val title: String? = null) : Parcelable,
    BaseParcelable {
    override fun unique(): Any = id


    companion object{
        fun getHRRequests(ctx:Context) : List<ItemRequestType>{
            ctx.apply {
                return listOf(
                    ItemRequestType(RequestTypes.PHONE_ISSUE, getString(R.string.phone_issue_request)),
                    ItemRequestType(RequestTypes.SALARY_INFO, getString(R.string.salary_info_request)),
                    ItemRequestType(RequestTypes.HR_COMPLAIN , getString(R.string.complain_request)),
                    ItemRequestType(RequestTypes.PRINT_INSURANCE , getString(R.string.print_insurance_request)),
                    ItemRequestType(RequestTypes.MEDICAL_CARD , getString(R.string.medical_card_request)),
                    ItemRequestType(RequestTypes.MEDICAL_APPROVAL , getString(R.string.medical_approval_request)),
                    ItemRequestType(RequestTypes.AUTH_AREA , getString(R.string.auth_area_request)),
                    ItemRequestType(RequestTypes.BORROW , getString(R.string.borrow_request)),
                    ItemRequestType(RequestTypes.EXP_CERTIFICATE , getString(R.string.exp_certificate_request)),
                )
            }

        }

        fun getDMRequests(ctx:Context) : List<ItemRequestType>{
            ctx.apply {
                return listOf(
                    ItemRequestType(RequestTypes.HIRE_EMPLOYEE , getString(R.string.hire_emp_request)),
                    ItemRequestType(RequestTypes.RESIGN , getString(R.string.resign_request)),
                    ItemRequestType(RequestTypes.PENALTY , getString(R.string.penalty_request)),
                    ItemRequestType(RequestTypes.VACATION , getString(R.string.vacation_request)),
                    ItemRequestType(RequestTypes.EXCUSE , getString(R.string.excuse_request)),
                    ItemRequestType(RequestTypes.MISSION , getString(R.string.mission_request)),
                    ItemRequestType(RequestTypes.DM_COMPLAIN, getString(R.string.complain_request)),
                )
            }

        }

    }
}


enum class RequestTypes {
    PHONE_ISSUE,
    SALARY_INFO,
    HR_COMPLAIN,
    PRINT_INSURANCE,
    MEDICAL_CARD,
    MEDICAL_APPROVAL,
    AUTH_AREA,
    BORROW,
    EXP_CERTIFICATE,
    //DM
    HIRE_EMPLOYEE,
    RESIGN,
    PENALTY,
    VACATION,
    EXCUSE,
    MISSION,
    DM_COMPLAIN

}


