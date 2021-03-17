package com.gsgroup.hrapp.ui.fragment.notification

import android.os.Parcelable
import com.gsgroup.hrapp.base.BaseParcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NotificationItem(
    val id: Int = 0,
    val title: String? = null,
    val message: String? = null,
    val date: String? = null
) : Parcelable, BaseParcelable {
    override fun unique() = id

    companion object{
        fun getDummyList():List<NotificationItem> = listOf(
            NotificationItem(1,"Title1","Message1","10/2/2005"),
            NotificationItem(2,"Title1","Message1","10/2/2005"),
            NotificationItem(3,"Title1","Message1","10/2/2005")
        )
    }
}
