package com.gsgroup.hrapp.util

import android.content.Context
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.ui.fragment.sign_in_out_logs.LogsItem
import com.gsgroup.hrapp.util.CalendarUtil.CalendarDateType.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.*

class CalendarUtil(val ctx: Context, val year: Int, val month: Int) {

    // return num of days in specific month
    //this will be the max counter to loop in

    private fun getDaysInMonth(year: Int, month: Int): Int {
        val c = Calendar.getInstance()
        c[Calendar.YEAR] = year
        c[Calendar.MONTH] = month - 1
        return c.getActualMaximum(Calendar.DATE)
    }

    private fun getFirstDayNameInMonth(month: Int): Int {
        val cal = Calendar.getInstance()
        cal.time = Date()
        cal[Calendar.MONTH] = month - 1
        cal[Calendar.DAY_OF_MONTH] = Calendar.SATURDAY
        return when (cal.get(Calendar.DAY_OF_WEEK)) {
            Calendar.SUNDAY -> 2
            Calendar.MONDAY -> 3
            Calendar.TUESDAY -> 4
            Calendar.WEDNESDAY -> 5
            Calendar.THURSDAY -> 6
            Calendar.FRIDAY -> 7
            else -> 1
        }
    }

    fun setupFlow(): Flow<ArrayList<LogsItem>> {
        return flow {
            ctx.apply {
                val list = arrayListOf<LogsItem>()
                val days = getDaysInMonth(year, month)
                val startingDay = getFirstDayNameInMonth(month)
                var startPutDayNumbers = false
                var dayCounter = 1
                for (i in 0 until 7) { // to start with day names
                    val item = LogsItem()
                    item.dayName = getDayName(ctx, i)
                    item.textColor = getColorFromRes(R.color.black)
                    item.fabColor = getColorFromRes(R.color.white)
                    if (startingDay == i) {
                        startPutDayNumbers = true
                    }
                    if (startPutDayNumbers) {
                        item.dayNum = dayCounter
                        dayCounter += 1
                    }
                    list.add(item)
                }
                while (dayCounter <= days) {
                    val item = LogsItem()
                    item.dayNum = dayCounter
                    item.textColor = getColorFromRes(R.color.black)
                    item.fabColor = getColorFromRes(R.color.white)
                    list.add(item)
                    dayCounter += 1
                }
                this@flow.emit(list)
            }
        }.flowOn(Dispatchers.IO)
    }

    private fun getDayName(ctx: Context, i: Int): String? {
        ctx.apply {
            val res = resources.getStringArray(R.array.week_days)
            return res[i]
        }
    }


    private fun CalendarDateType.getTextColor(ctx: Context): Int {
        ctx.apply {
            return when (this@getTextColor) {
                CASUAL_VACATION -> getColorFromRes(R.color.blue_btn_bg_color)
                FRIDAY_VACATION -> getColorFromRes(R.color.red_btn_bg_color)
                else -> getColorFromRes(R.color.white)
            }
        }

    }


    enum class CalendarDateType {
        CASUAL_VACATION,
        FRIDAY_VACATION
    }

}