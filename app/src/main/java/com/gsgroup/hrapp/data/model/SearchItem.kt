package com.gsgroup.hrapp.data.model

import android.os.Parcelable
import com.gsgroup.hrapp.base.BaseParcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchItem(val id: Int = 0, val name: String? = null) : Parcelable, SearchItemInterface {
    override fun id(): Int = id
    override fun name(): String? = name
    override fun unique(): Any = id

    companion object {
        fun getDummyData() = arrayOf<SearchItemInterface>(
            SearchItem(1, "List name1"),
            SearchItem(2, "name2"),
            SearchItem(3, "mahmoud"),
            SearchItem(4, "lk"),
        )
    }
}

interface SearchItemInterface : Parcelable, BaseParcelable {
    fun id(): Int?
    fun name(): String?
}