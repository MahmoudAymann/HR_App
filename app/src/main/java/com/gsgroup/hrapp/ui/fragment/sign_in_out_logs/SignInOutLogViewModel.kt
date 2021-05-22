package com.gsgroup.hrapp.ui.fragment.sign_in_out_logs

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.AndroidBaseViewModel
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.data.model.SearchItemInterface
import com.gsgroup.hrapp.ui.fragment.login.Attendance
import com.gsgroup.hrapp.ui.fragment.map.AttendanceResponse
import com.gsgroup.hrapp.util.CalendarUtil
import com.gsgroup.hrapp.util.Resource
import com.gsgroup.hrapp.util.isNull
import com.gsgroup.hrapp.util.requestNewCallDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class SignInOutLogViewModel(app: Application) : AndroidBaseViewModel(app) {

    val colorsAdapter = ColorsAdapter()
    val logsAdapter = LogsAdapter(::onDayClick)
    val obsMonth = ObservableField(app.getString(R.string.select_month))
    val obsYear = ObservableField(app.getString(R.string.select_year))
    val obsShowResult = ObservableBoolean()
    val obsShowDays = ObservableBoolean()
    val request = AttendanceLogRequest()

    var userId: String? = null
    var item : Attendance?=null
    private fun showCalendarView(year: Int?, month: Int?) {
        if (year.isNull() || month.isNull()) return
        viewModelScope.launch(Dispatchers.Main) {
            CalendarUtil(app.applicationContext, year!!, month!!).setupFlow().collect {
                logsAdapter.setList(it)
                obsShowDays.set(true)
            }
        }
    }

    private fun onDayClick(item: LogsItem) {
        obsShowResult.set(false)
        request.day = item.dayNum.toString()
        requestNewCallDeferred({ getAttendanceCallAsync() }) {
            postResult(Resource.success())
            this.item = it.response?.data
            notifyChange()
            obsShowResult.set(true)
        }
    }

    private fun getAttendanceCallAsync() =
        apiHelper.getAttendanceLogsAsync(
            "${request.year}-${editNumberToServerRequest(request.month)}-${
                editNumberToServerRequest(request.day)
            }",
            userId
    )

    private fun editNumberToServerRequest(date: String?): String {
        return date?.let {
            if (it.length == 1) {
                "0$it"
            } else
                it
        } ?: "00"
    }


    fun onMonthClick() {
        setValue(Codes.SELECT_MONTH)
    }

    fun onYearClick() {
        setValue(Codes.SELECT_YEAR)

    }

    fun gotData(args: SignInOutLogFragmentArgs) {
        args.userId?.let {
            userId = it
        }
    }

    fun gotResultFromBottomSheet(it: SearchItemInterface?) {
        if (it is ItemMonth) {
            obsMonth.set(it.name)
            request.month = it.id.toString()
        } else if (it is ItemYear) {
            obsYear.set(it.name())
            request.year = it.name
        }
        if (request.isValid()) {
            showCalendarView(request.year?.toInt(), request.month?.toInt())
        }
    }

    init {
        colorsAdapter.setList(ItemColor.getDummyData(app))
    }


}