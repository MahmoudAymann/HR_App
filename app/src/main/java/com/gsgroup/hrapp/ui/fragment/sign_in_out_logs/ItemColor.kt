package com.gsgroup.hrapp.ui.fragment.sign_in_out_logs

import android.app.Application
import android.os.Parcelable
import androidx.annotation.ColorRes
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseParcelable
import com.gsgroup.hrapp.util.getColorFromRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemColor(val id: Int = 0, val name: String? = null, val color: Int? = null) :
    Parcelable, BaseParcelable {
    override fun unique(): Any = id
    companion object {
        fun getDummyData(app: Application) = arrayListOf(
            ItemColor(1, "name1", app.getColorFromRes(R.color.blue_btn_bg_color)),
            ItemColor(1, "name1", app.getColorFromRes(R.color.blue_btn_bg_color)),
            ItemColor(1, "name1", app.getColorFromRes(R.color.red)),
            ItemColor(1, "name1", app.getColorFromRes(R.color.blue_btn_bg_color)),
            ItemColor(1, "name1", app.getColorFromRes(R.color.blue_btn_bg_color))
        )
    }
}