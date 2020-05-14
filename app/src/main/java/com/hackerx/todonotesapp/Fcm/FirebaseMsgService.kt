package com.hackerx.todonotesapp.Fcm

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.hackerx.todonotesapp.R
import kotlinx.android.synthetic.main.notes_adapter_layout.view.*

class FirebaseMsgService : FirebaseMessagingService() {
    val TAG = "Firebase FCM"
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.d(TAG, message.from)
        Log.d(TAG, message.data.toString())
        setupNotifcation(message?.notification?.body)
    }

    private fun setupNotifcation(body: String?) {
        val channelID = "Todo ID"
        val ringtone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this,channelID)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Todo Notes App")
                .setContentText(body)
                .setSound(ringtone)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.O)
        {
           val channel = NotificationChannel(channelID,"Todo-Notes",NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(0,notificationBuilder.build())
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }
}
