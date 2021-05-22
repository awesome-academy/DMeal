package com.sunasterisk.dmealfoodapp.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.sunasterisk.dmealfoodapp.receiver.NotificationReceiver
import java.util.*

object Notification {
    fun enable(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val pendingIntent = PendingIntent.getBroadcast(context, 0, getIntent(context), 0)
        val currentCal: Calendar = Calendar.getInstance()
        val calendar: Calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 16)
            set(Calendar.MINUTE, 19)
            set(Calendar.SECOND, 0)
        }
        var intendedTime = calendar.timeInMillis
        val currentTime = currentCal.timeInMillis
        if (intendedTime >= currentTime) {
            alarmManager.setRepeating(
                AlarmManager.RTC,
                intendedTime,
                AlarmManager.INTERVAL_DAY,
                pendingIntent
            )
        } else {
            calendar.add(Calendar.DAY_OF_MONTH, 1)
            intendedTime = calendar.timeInMillis
            alarmManager.setRepeating(
                AlarmManager.RTC,
                intendedTime,
                AlarmManager.INTERVAL_DAY,
                pendingIntent
            )
        }
    }

    fun disable(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val pendingIntent = PendingIntent.getBroadcast(context, 0, getIntent(context), 0)
        alarmManager.cancel(pendingIntent)
    }

    private fun getIntent(context: Context) = Intent(context, NotificationReceiver::class.java)
}
