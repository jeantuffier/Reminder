package fr.jeantuffier.reminder.free.common.service

import android.annotation.TargetApi
import android.app.IntentService
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.support.v4.app.TaskStackBuilder
import android.text.format.DateFormat
import fr.jeantuffier.reminder.R
import fr.jeantuffier.reminder.free.common.extension.getIntent
import fr.jeantuffier.reminder.free.common.model.Priority
import fr.jeantuffier.reminder.free.common.model.Task
import fr.jeantuffier.reminder.free.home.presentation.HomeActivity
import java.text.SimpleDateFormat
import java.util.*


private const val VIBRATION_TIME: Long = 250
private const val BLINKING_TIME = 3000

private const val CHANNEL_ID = "display_notification_service_channel_id"
private const val CHANNEL_NAME = "DisplayNotificationService"

class DisplayNotificationService : IntentService("DisplayNotificationService") {

    override fun onHandleIntent(intent: Intent?) {
        val id = intent?.extras?.getInt(Task.ID)
        val text = intent?.extras?.getString(Task.TITLE)
        val priority = intent?.extras?.getString(Task.PRIORITY)
        val from = intent?.extras?.getString(Task.FROM)
        val to = intent?.extras?.getString(Task.TO)

        if (text != null && id != null && priority != null && from != null && to != null) {
            createNotification(id, text, priority, from, to)
        }
    }

    private fun createNotification(id: Int, text: String, priority: String, from: String, to: String) {
        if (Build.VERSION.SDK_INT >= 26) {
            initChannel(text, priority)
        }

        val builder = NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_replay)
                .setContentTitle(getString(R.string.notification_title))
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVibrate(longArrayOf(VIBRATION_TIME, VIBRATION_TIME, VIBRATION_TIME, VIBRATION_TIME))
                .setLights(Color.RED, BLINKING_TIME, BLINKING_TIME)
        val resultIntent = getIntent<HomeActivity>()

        val stackBuilder = TaskStackBuilder.create(this)
        stackBuilder.addNextIntent(resultIntent)
        val resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        builder.setContentIntent(resultPendingIntent)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (isInTimeInterval(from, to)) {
            notificationManager.notify(id, builder.build())
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private fun initChannel(text: String, priority: String) {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val importance = when (priority) {
            Priority.HIGH.toString() -> NotificationManager.IMPORTANCE_HIGH
            Priority.MIDDLE.toString() -> NotificationManager.IMPORTANCE_DEFAULT
            else -> NotificationManager.IMPORTANCE_LOW
        }

        val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance)
        channel.description = text
        notificationManager.createNotificationChannel(channel)
    }

    private fun isInTimeInterval(from: String, to: String): Boolean {
        val dateFrom = getCalendarDateFromString(from)
        val dateTo = getCalendarDateFromString(to)

        val current = Calendar.getInstance()

        return current.after(dateFrom) && current.before(dateTo)
    }

    private fun getCalendarDateFromString(string: String) : Calendar {
        val values = string.split(":")
        val date = Calendar.getInstance()
        date.set(Calendar.HOUR_OF_DAY, values[0].toInt())
        date.set(Calendar.MINUTE, values[1].toInt())
        return date
    }

}
