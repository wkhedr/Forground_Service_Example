package com.example.serviceapplication

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyService : Service() {
    var isServiceRunning = false
    lateinit var launch: kotlinx.coroutines.Job
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
    override fun onCreate() {
        super.onCreate()
        Log.v("MyService", "onCreate")

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action){
            "START" -> {
                if (!isServiceRunning) {
                    foregroundStart()
                    isServiceRunning = true
                    doOperation()
                }
            }
            "STOP" -> {
                stopSelf()
                launch.cancel()
                isServiceRunning = false
            }
        }

        return super.onStartCommand(intent, flags, startId)
    }

    private fun doOperation() {
        launch = CoroutineScope(Dispatchers.Default).launch {
            for (i in 1..100) {
                Log.v("MyService", "doOperation $i")
                delay(1000L) // Use delay instead of Thread.sleep
            }
        }
    }

    private fun foregroundStart() {
        lateinit var MyNotification: Notification
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notificationChannel =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel("123", "MyService", NotificationManager.IMPORTANCE_DEFAULT)
            } else {
                null
            }

        if (notificationChannel != null) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                MyNotification = NotificationCompat.Builder(this, notificationChannel.id)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle("MyService")
                    .setContentText("MyService is running")
                    .build()
                notificationManager.createNotificationChannel(notificationChannel)
            }
        }

        else{
            MyNotification = NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("MyService")
                .setContentText("MyService is running")
                .build()
        }
        startForeground(1, MyNotification)

    }
}