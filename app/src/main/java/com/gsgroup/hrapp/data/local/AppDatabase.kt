package com.gsgroup.hrapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gsgroup.hrapp.data.local.entity.CalendarItem
import com.gsgroup.hrapp.data.local.dao.CalendarDao


@Database(entities = [CalendarItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun calendarDao() : CalendarDao
}