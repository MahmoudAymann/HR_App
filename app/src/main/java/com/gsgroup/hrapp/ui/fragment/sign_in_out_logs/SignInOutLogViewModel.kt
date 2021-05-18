package com.gsgroup.hrapp.ui.fragment.sign_in_out_logs

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.AndroidBaseViewModel
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.data.model.SearchItemInterface
import com.gsgroup.hrapp.util.CalendarUtil
import com.gsgroup.hrapp.util.Resource
import com.gsgroup.hrapp.util.requestNewCallDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

@InternalCoroutinesApi
class SignInOutLogViewModel(app: Application) : AndroidBaseViewModel(app) {

    val colorsAdapter = ColorsAdapter()
    val logsAdapter = LogsAdapter(::onDayClick)
    val obsMonth = ObservableField(app.getString(R.string.select_month))
    val obsYear = ObservableField(app.getString(R.string.select_year))
    val obsShowResult = ObservableBoolean()
    val obsShowDays = ObservableBoolean()
    val request = AttendanceLogRequest()


    init {
        showDays()
    }


    private fun showDays(){
        viewModelScope.launch(Dispatchers.Main) {
            CalendarUtil(app.applicationContext, 2021,5).setupFlow().collect {
                logsAdapter.setList(it)
                obsShowDays.set(true)
            }
        }
    }

    private fun onDayClick(item: LogsItem) {
        request.day = item.dayNum.toString()
        requestNewCallDeferred({ getAttendanceCallAsync() }) {
            postResult(Resource.success())
        }
    }

    private fun getAttendanceCallAsync() =
        apiHelper.getAttendanceLogsAsync("${request.year}-${request.month}-${request.day}")


    fun onMonthClick() {
        setValue(Codes.SELECT_MONTH)
    }

    fun onYearClick() {
        setValue(Codes.SELECT_YEAR)

    }

    fun gotResultFromBottomSheet(it: SearchItemInterface?) {
        if (it is ItemMonth) {
            obsMonth.set(it.name)
            request.month = it.id.toString()
        } else if (it is ItemYear) {
            obsYear.set(it.name())
            request.year = it.name
        }
    }

    init {
        colorsAdapter.setList(ItemColor.getDummyData(app))
    }


}