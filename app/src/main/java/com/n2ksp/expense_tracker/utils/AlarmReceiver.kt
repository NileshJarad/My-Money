package com.n2ksp.expense_tracker.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            LocalNotificationUtils.showNotification(it)
        }

    }

}