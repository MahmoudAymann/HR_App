package com.gsgroup.hrapp.model

import android.content.Context
import android.os.Parcelable
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseParcelable
import kotlinx.parcelize.Parcelize


enum class HomeIds {
    DIRECT_MANAGER,
    REQUESTS,
    SIGN_IN_OUT_LOGS,
    HR,
    MY_TEAM,
    COMPANY_POLICES
}

fun HomeIds.toFragmentStringName(): String {
    return ""
//    return when (this) {
//        HomeIds.DIRECT_MANAGER -> FragmentDirectManger::class.java.name
//        HomeIds.REQUESTS -> FragmentChooseRequests::class.java.name
//        HomeIds.SIGN_IN_OUT_LOGS -> FragmentSignInOut::class.java.name
//        HomeIds.HR -> HrPersonalFragment::class.java.name
//        HomeIds.MY_TEAM -> FragmentMyTeam::class.java.name
//        HomeIds.COMPANY_POLICES -> FragmentCompanyPolices::class.java.name
//    }
}

@Parcelize
data class HomeItem(
    var id: HomeIds,
    var title: String?,
    var icon: Int
) : BaseParcelable, Parcelable {
    companion object {
        fun getHomeList(context: Context): ArrayList<HomeItem> {
            return arrayListOf(
                HomeItem(
                    HomeIds.DIRECT_MANAGER,
                    context.getString(R.string.direct_manger),
                    R.drawable.ic_direct_manager
                ),
                HomeItem(
                    HomeIds.REQUESTS,
                    context.getString(R.string.requests),
                    R.drawable.ic_requests
                ),
                HomeItem(
                    HomeIds.SIGN_IN_OUT_LOGS,
                    context.getString(R.string.sign_in_out_logs),
                    R.drawable.ic_sign_in_out
                ),
                HomeItem(
                    HomeIds.HR,
                    context.getString(R.string.my_hr),
                    R.drawable.ic_hr
                ),
                HomeItem(
                    HomeIds.MY_TEAM,
                    context.getString(R.string.my_team),
                    R.drawable.ic_my_team
                ),
                HomeItem(
                    HomeIds.COMPANY_POLICES,
                    context.getString(R.string.company_policy),
                    R.drawable.ic_company_polices
                ),
            )
        }
    }

    override fun unique(): Any = id
}

