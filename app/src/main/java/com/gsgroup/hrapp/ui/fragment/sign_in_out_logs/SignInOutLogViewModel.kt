package com.gsgroup.hrapp.ui.fragment.sign_in_out_logs

import android.app.Application
import androidx.databinding.ObservableField
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.AndroidBaseViewModel

class SignInOutLogViewModel(app: Application) : AndroidBaseViewModel(app) {

    val colorsAdapter = ColorsAdapter()
    val logsAdapter = LogsAdapter(::onDayClick)
    val obsMonth = ObservableField(app.getString(R.string.select_month))
    val obsYear = ObservableField(app.getString(R.string.select_year))

    private fun onDayClick(item: LogsItem) {

    }

    init {
        colorsAdapter.setList(ItemColor.getDummyData(app))
    }


}