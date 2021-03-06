package com.ddd.app.fcm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.ddd.R
import com.ddd.presentation.ui.main.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class DDDFirebaseMsgService : FirebaseMessagingService() {

    companion object {
        const val TITLE_CHANNEL_READABLE = "DDD Push channel"
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)
        remoteMessage?.run {
            sendNotification(
                notification?.title.orEmpty(),
                notification?.body.orEmpty(),
                notificationBuilder
            )
        } ?: return
    }

    private val notificationBuilder: (channelId: String, title: String, msg: String, intent: PendingIntent) -> NotificationCompat.Builder =
        { channelId, title, msg, intent ->
            NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_notification_ddd)
                .setContentTitle(title)
                .setContentText(msg)
                .setAutoCancel(true)
                .setContentIntent(intent)

        }

    private fun sendNotification(
        title: String,
        msg: String,
        notificationBuilder: (channelId: String, title: String, msg: String, intent: PendingIntent) -> NotificationCompat.Builder
    ) {
        val intent = Intent(this, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        val channelId = getString(R.string.default_notification_channel_id)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(
                channelId,
                TITLE_CHANNEL_READABLE,
                NotificationManager.IMPORTANCE_DEFAULT
            ).let(notificationManager::createNotificationChannel)
        }

        notificationManager.notify(
            1,
            notificationBuilder(channelId, title, msg, pendingIntent).build()
        )
    }
}
