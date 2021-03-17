package com.gsgroup.hrapp.ui.fragment.myteam

import android.os.Parcelable
import com.gsgroup.hrapp.base.BaseParcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MyTeamItem(
    val id: Int = 0,
    val name: String? = null,
    val jobTitle: String? = null,
    val code: String? = null,
    val company: String? = null,
) : Parcelable, BaseParcelable {
    override fun unique() = id

    companion object{
        fun getDummyList():List<MyTeamItem> = listOf(
            MyTeamItem(1,"Ali","Android","AM_10","Aman"),
            MyTeamItem(2,"Ali","Android","AM_10","Aman"),
            MyTeamItem(3,"Ali","Android","AM_10","Aman")
        )
    }
}
