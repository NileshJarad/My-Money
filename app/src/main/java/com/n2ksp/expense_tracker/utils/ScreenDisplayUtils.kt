package com.n2ksp.expense_tracker.utils

import android.app.Activity
import android.graphics.Point


object ScreenDisplayUtils {

    fun getScreeSize(context: Activity): Pair<Int, Int> {
        val display = context.windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val width = size.x
        val height = size.y
        return Pair(width, height)
    }
}