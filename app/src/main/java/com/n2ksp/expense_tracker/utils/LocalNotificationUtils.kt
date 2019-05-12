package com.n2ksp.expense_tracker.utils


import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.PowerManager
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import com.n2ksp.expense_tracker.R
import com.n2ksp.expense_tracker.ui.main.MainActivity
import java.util.*


object LocalNotificationUtils {

    fun checkBatteryOptimized(context: Context): Boolean {
        val pwrm = context.getSystemService(Context.POWER_SERVICE) as PowerManager
        val name = context.packageName
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return !pwrm.isIgnoringBatteryOptimizations(name)

        }
        return false
    }

    fun setupDailyNotification(context: Context) {
        val manager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val alarmIntent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0)

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.set(Calendar.HOUR_OF_DAY, 22)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)

        val time = calendar.timeInMillis

        manager.setRepeating(
            AlarmManager.RTC_WAKEUP, time,
            AlarmManager.INTERVAL_DAY, pendingIntent
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            manager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, time, pendingIntent);
        }
    }

    fun showNotification(context: Context) {
//        setupDailyNotification(context)
        val intent = Intent(context, MainActivity::class.java)

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notificationId = System.currentTimeMillis().toInt()
        val channelId = "channel-01"
        val channelName = "Channel Name"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val mChannel = NotificationChannel(
                channelId, channelName, importance
            )
            notificationManager.createNotificationChannel(mChannel)
        }

        val mBuilder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_notification_icon)
            .setContentTitle("Reminder")
            .setContentText("Don't forget to add your expenses or income")

        val stackBuilder = TaskStackBuilder.create(context)
        stackBuilder.addNextIntent(intent)
        val resultPendingIntent = stackBuilder.getPendingIntent(
            0,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        mBuilder.setContentIntent(resultPendingIntent)

        notificationManager.notify(notificationId, mBuilder.build())
    }
}