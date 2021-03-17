package com.gsgroup.hrapp.ui.fragment.company_polices

import android.os.Parcelable
import com.gsgroup.hrapp.base.BaseParcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CompanyPolicyItem(
    val id: Int = 0,
    val title: String? = null
) : Parcelable, BaseParcelable {
    override fun unique() = id

    companion object{
        fun getDummyList():List<CompanyPolicyItem> = listOf(
            CompanyPolicyItem(1,"Title1"),
            CompanyPolicyItem(2,"Title2"),
            CompanyPolicyItem(3,"Title3")
        )
    }
}
