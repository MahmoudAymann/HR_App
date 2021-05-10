package com.gsgroup.hrapp.ui.fragment.sign_in_out_logs

import android.content.Context
import android.os.Parcelable
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.data.model.SearchItemInterface
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemMonth(val id: Int = 0, val name: String? = null) : Parcelable, SearchItemInterface {
    override fun id(): Int = id

    override fun name(): String? = name


    companion object {
        fun getMonthList(ctx: Context): Array<SearchItemInterface> {
            val myList = arrayListOf<ItemMonth>()
            val stringMonths = ctx.resources.getStringArray(R.array.months)
            for (i in stringMonths.indices) {
                myList.add(ItemMonth(i + 1, stringMonths[i]))
            }

            return myList.toTypedArray()
        }
    }
}