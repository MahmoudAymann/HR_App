package com.gsgroup.hrapp.ui.fragment.chat

import android.app.Application
import android.os.Parcelable
import com.gsgroup.hrapp.base.BaseParcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChatItem(
    val id: Int = 0,
    val name: String? = null,
    val message: String? = null,
    val date: String? = null,
    val time: String? = null
) : Parcelable, BaseParcelable {
    override fun unique() = id

    companion object {
        fun getDummyList(): List<ChatItem> =
            listOf(ChatItem(1,
                "User1",
                "I'm fine",
                "15/05/2020",
                "now"),
                ChatItem(2,
                "User2",
                "Hi, How Are you!",
                "10/05/2020",
                "2 sec ago")
            )
    }

}
