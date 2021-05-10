package com.gsgroup.hrapp.data.local

import com.gsgroup.hrapp.data.local.AppDatabase
import com.gsgroup.hrapp.data.local.DatabaseHelper
import com.gsgroup.hrapp.data.local.entity.CalendarItem

//class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {
//    override suspend fun getCalendarDays() = appDatabase.calendarDao().getAll()
//
//    override suspend fun insertAll(list: List<CalendarItem>) =
//        appDatabase.calendarDao().insertAll(list)
//}