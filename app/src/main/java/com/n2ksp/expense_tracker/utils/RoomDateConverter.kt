package com.n2ksp.expense_tracker.utils

import androidx.room.TypeConverter
import java.util.*


class RoomDateConverter {
    @TypeConverter
    fun toDate(dateLong: Long?): Date? {
        return if (dateLong == null) null else Date(dateLong)
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return (date?.time)
    }
}