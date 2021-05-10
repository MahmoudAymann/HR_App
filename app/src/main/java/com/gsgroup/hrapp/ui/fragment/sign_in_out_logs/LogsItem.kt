package com.gsgroup.hrapp.ui.fragment.sign_in_out_logs

import android.content.Context
import android.os.Parcelable
import androidx.annotation.ColorRes
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseParcelable
import com.gsgroup.hrapp.util.AppUtil
import com.gsgroup.hrapp.util.getColorFromRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class LogsItem(
    var dayNum: Int = 0,
    var dayName: String? = null,
    var textColor: Int? = null,
    var fabColor: Int? = null
) : Parcelable, BaseParcelable {
    override fun unique() = dayNum
}
