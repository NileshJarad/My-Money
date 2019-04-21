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


    @SuppressLint("SimpleDateFormat")
    fun getCurrentMonth(): String {
        val cal = Calendar.getInstance()
        return SimpleDateFormat("MMM").format(cal.time)
    }

    fun isToday(day: Int): Boolean {
        return getCurrentDayOfMonth() == day
    }
}