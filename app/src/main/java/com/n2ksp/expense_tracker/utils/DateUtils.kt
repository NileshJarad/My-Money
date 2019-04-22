package com.n2ksp.expense_tracker.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*


object DateUtils {

    fun getCurrentDayOfMonth(): Int {
        val cal = Calendar.getInstance()
        cal.time = Date()
        return cal.get(Calendar.DAY_OF_MONTH)
    }

    fun getCurrentMonthInt(): Int {
        val cal = Calendar.getInstance()
        cal.time = Date()
        return cal.get(Calendar.MONTH)
    }


    @SuppressLint("SimpleDateFormat")
    fun getCurrentMonth(): String {
        val cal = Calendar.getInstance()
        return SimpleDateFormat("MMM").format(cal.time)
    }

    fun isToday(day: Int): Boolean {
        return getCurrentDayOfMonth() == day
    }

    fun getDatesForDay(day: Int, month: Int): Pair<Date, Date> {
        val dateToday = GregorianCalendar()
        // reset hour, minutes, seconds and millis

        dateToday.set(Calendar.MONTH, month)
        dateToday.set(Calendar.DAY_OF_MONTH, day)


        dateToday.set(Calendar.HOUR_OF_DAY, 0)
        dateToday.set(Calendar.MINUTE, 0)
        dateToday.set(Calendar.SECOND, 0)
        dateToday.set(Calendar.MILLISECOND, 0)

        val startDayToday = dateToday.time

        dateToday.set(Calendar.HOUR_OF_DAY, 23)
        dateToday.set(Calendar.MINUTE, 59)
        dateToday.set(Calendar.SECOND, 59)
        dateToday.set(Calendar.SECOND, 999)

        val endDayToday = dateToday.time

        return Pair(startDayToday, endDayToday)
    }

}