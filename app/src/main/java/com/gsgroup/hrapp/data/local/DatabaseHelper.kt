package com.gsgroup.hrapp.data.local

import com.gsgroup.hrapp.data.local.entity.CalendarItem

interface DatabaseHelper {

    suspend fun getCalendarDays(): List<CalendarItem>

    suspend fun insertAll(users: List<CalendarItem>)

}