package com.gsgroup.hrapp.ui.fragment.faq

import android.os.Parcelable
import com.gsgroup.hrapp.base.BaseParcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FAQItem(
    val id: Int = 0,
    val title: String? = null,
    val message: String? = null
) : Parcelable, BaseParcelable {
    override fun unique() = id

    companion object{
        fun getDummyList():List<FAQItem> = listOf(
            FAQItem(1,"Title1","Message1"),
            FAQItem(2,"Title2","Message1"),
            FAQItem(3,"Title3","Message1")
        )
    }
}
