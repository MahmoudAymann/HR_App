package com.gsgroup.hrapp.ui.customeviews

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.util.AppUtil

class LogoTextView(context: Context) : AppCompatTextView(context) {

    init {
        setup(context)
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : this(context) {
        setup(context)
    }

    constructor(context: Context, attributeSet: AttributeSet): this(context) {
        setup(context)
    }

    private fun setup(context: Context) {
        text = context.getString(
            R.string.powered_by_gs_group, when (AppUtil.isOldDevice()) {
                true -> context.getString(R.string.app_version_lite)
                else -> context.getString(R.string.app_version_name)
            },context.getString(R.string.app_version))
        setTextColor(Color.WHITE)
        setPadding(8,8,8,8)
    }

}