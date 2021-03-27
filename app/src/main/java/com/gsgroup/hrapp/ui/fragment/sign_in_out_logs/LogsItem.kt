package com.gsgroup.hrapp.ui.fragment.sign_in_out_logs

import android.os.Parcelable
import com.gsgroup.hrapp.base.BaseParcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LogsItem(
    val id: Int = 0,
    val title: String? = null,
    val message: String? = null,
    val date: String? = null
) : Parcelable, BaseParcelable {
    override fun unique() = id

    companion object{
        fun getDummyList():List<LogsItem> = listOf(
            LogsItem(1,"Title1","Message1","10/2/2005"),
            LogsItem(2,"Title1","Message1","10/2/2005"),
            LogsItem(3,"Title1","Message1","10/2/2005")
        )
    }
}
