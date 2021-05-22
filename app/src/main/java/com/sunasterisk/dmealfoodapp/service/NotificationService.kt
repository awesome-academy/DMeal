package com.sunasterisk.dmealfoodapp.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.JobIntentService
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.bumptech.glide.request.target.NotificationTarget
import com.sunasterisk.dmealfoodapp.R
import com.sunasterisk.dmealfoodapp.data.model.Meal
import com.sunasterisk.dmealfoodapp.data.repository.MealRepository
import com.sunasterisk.dmealfoodapp.data.source.local.MealLocalDataSource
import com.sunasterisk.dmealfoodapp.data.source.local.dao.MealDaoImpl
import com.sunasterisk.dmealfoodapp.data.source.local.db.AppDatabase
import com.sunasterisk.dmealfoodapp.data.source.remote.MealRemoteDataSource
import com.sunasterisk.dmealfoodapp.data.source.ultils.OnDataCallback
import com.sunasterisk.dmealfoodapp.ui.main.MainActivity
import com.sunasterisk.dmealfoodapp.utils.loadImage
import com.sunasterisk.dmealfoodapp.utils.showToast

class NotificationService : JobIntentService() {

    override fun onHandleWork(intent: Intent) {
        val repository = MealRepository.getInstance(
            MealRemoteDataSource.getInstance(),
            MealLocalDataSource.getInstance(
                MealDaoImpl.getInstance(
                    AppDatabase.getInstance(applicationContext)
                )
            )
        )
        repository.searchMeal("", object : OnDataCallback<List<Meal>> {
            override fun onSuccess(data: List<Meal>) {
                showNotification(data.random())
            }

            override fun onFailure(throwable: Throwable) {
                this@NotificationService.showToast(throwable.message.toString())
            }
        })
    }

    private fun createChannel() {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun showNotification(meal: Meal) {
        createChannel()
        showForegroundNoti(meal)
    }

    private fun showForegroundNoti(meal: Meal) {
        val collapsedView = RemoteViews(
            packageName,
            R.layout.layout_notification_collapsed
        )
        val expandedView = RemoteViews(
            packageName,
            R.layout.layout_notification_expanded
        )
        expandedView.setTextViewText(R.id.textTitleNotify, meal.name)

        val pendingIntent =
            PendingIntent.getActivity(
                this,
                0,
                MainActivity.getIntent(this),
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_logo)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setCustomContentView(collapsedView)
            .setCustomBigContentView(expandedView)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()
        val notificationManagerCompat = NotificationManagerCompat.from(this)
        notificationManagerCompat.notify(NOTIFICATION_ID, builder)
        loadImageToNotification(builder, expandedView, meal)
    }

    private fun loadImageToNotification(
        notification: Notification,
        views: RemoteViews,
        meal: Meal
    ) {
        val target = NotificationTarget(
            this,
            R.id.imageNotify,
            views,
            notification,
            NOTIFICATION_ID
        )
        loadImage(meal.image, this@NotificationService, target)
    }

    companion object {
        private const val CHANNEL_ID = "NotifyMealChannel"
        private const val JOB_ID = 147
        private const val CHANNEL_NAME = "DMeal"
        const val NOTIFICATION_ID = 123

        fun enqueueWork(context: Context, intent: Intent) {
            enqueueWork(context, NotificationService::class.java, JOB_ID, intent)
        }
    }
}
