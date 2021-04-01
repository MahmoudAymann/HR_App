package com.gsgroup.hrapp.ui.customeviews

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
class BlackTextView(context: Context): AppCompatTextView(context) {


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
        setTextColor(Color.BLACK)
    }


}