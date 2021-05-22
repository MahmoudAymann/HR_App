package com.gsgroup.hrapp.ui.fragment.requests.mission

import android.app.Application
import androidx.databinding.ObservableBoolean
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.AndroidBaseViewModel
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.util.Resource
import com.gsgroup.hrapp.util.requestNewCallDeferred
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

class MissionViewModel(app: Application) : AndroidBaseViewModel(app) {

    val request = MissionRequestRequest()

    val obsFullDay = ObservableBoolean()
    private lateinit var myTimeFromCalendar: Calendar
    private lateinit var myTimeToCalendar: Calendar
    fun onFullDayClick() {
        if (obsFullDay.get()) {
            obsFullDay.set(false)
            request.fullTime = MissionRequestRequest.FULL_TIME_OFF
        } else {
            obsFullDay.set(true)
            request.fullTime = MissionRequestRequest.FULL_TIME_ON
            request.toTime = null
            request.fromTime = null
            notifyChange()

        }
    }


    fun onDateClick(code: Int) {
        setValue(code)
    }


    fun onDateSelected(code: Int, date: String) {
        if (code == Codes.OPEN_DATE_FROM) {
            request.fromDate = date
        } else {
            request.toDate = date
        }
        notifyChange()
    }

    fun onTimeClick(code: Int) {
        setValue(code)
    }


    fun onTimeSelected(code: Int, calendar: Calendar) {
        if (code == Codes.OPEN_TIME_FROM) {
            myTimeFromCalendar = calendar
            request.fromTime = getDateInFormat(false, calendar)
            notifyChange()
        } else {
            myTimeToCalendar = calendar
            request.toTime = getDateInFormat(false,calendar)
            notifyChange()
        }
    }

    fun onSubmitClick() {
        if (request.isValid()) {
            if (!obsFullDay.get()) {
                request.fromTime = getDateInFormat(true, myTimeFromCalendar)
                request.toTime = getDateInFormat(true, myTimeToCalendar)
            }
            requestNewCallDeferred({ missionRequestDefAsync() }) {
                postResult(Resource.success(msg = it.message))
            }
        } else {
            postResult(Resource.message(app.getString(R.string.all_data_required)))
        }
    }

    private fun missionRequestDefAsync() = apiHelper.missionRequestAsync(request)


    private fun getDateInFormat(is_24: Boolean, calendar: Calendar): String {
        return if (is_24) {
            val timeIn24Format = SimpleDateFormat("HH:mm:ss", Locale.ENGLISH)
            timeIn24Format.format(calendar.time)
        } else {
            val timeIn12Format = SimpleDateFormat("hh:mm aa", Locale.ENGLISH)
            timeIn12Format.format(calendar.time)
        }
    }

}