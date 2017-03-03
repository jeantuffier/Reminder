package fr.jeantuffier.reminder.common.jobscheduler

import android.app.IntentService
import android.app.job.JobParameters
import android.content.Intent
import android.app.PendingIntent
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.support.v4.app.NotificationCompat
import android.support.v4.app.TaskStackBuilder
import fr.jeantuffier.reminder.R
import fr.jeantuffier.reminder.common.model.Task
import fr.jeantuffier.reminder.display.view.DisplayTaskActivity
import no.hyper.memoryorm.Memory
import java.util.*

/**
 * Created by jean on 21.11.2016.
 */
class DisplayNotificationService : IntentService("DisplayNotificationService") {

    private val VIBRATION_TIME : Long = 250
    private val BLINKING_TIME = 3000

    override fun onHandleIntent(intent: Intent) {
        super.onCreate()

        val params = intent.extras
        val taskId = params?.getString(Task.ID)
        taskId ?: return

        if (!JobManager.isRegistered(this, taskId)) {
            Memory(this).fetchById(Task::class.java, taskId)?.let {
                JobManager.unregisterJob(this, it)
            }
        }

        val text = params?.getString(Task.TITLE)

        val from = params?.getString(Task.FROM)
        val fromValues = getTimeValues(from)

        val to = params?.getString(Task.TO)
        val toValues = getTimeValues(to)

        val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val currentMinute = Calendar.getInstance().get(Calendar.MINUTE)

        if (checkTimeValues(listOf(fromValues[0], fromValues[1], toValues[0], toValues[1]))) {
            val isOverMinimal = currentHour > fromValues[0]
                    || (currentHour == fromValues[0] && currentMinute >= fromValues[1])
            val isUnderMaximal = currentHour < toValues[0]
                    || (currentHour == toValues[1] && currentMinute <= toValues[1])
            if (isOverMinimal && isUnderMaximal) {
                createNotification(text, taskId.toInt())
            }
        } else {
            createNotification(text, taskId.toInt())
        }
    }

    private fun getTimeValues(base: String?) : IntArray {
        var hours = 0
        var minutes = 0
        if (base != null && base.isNotEmpty()) {
            val parts = base.split(":")
            hours = parts[0].toInt()
            minutes = parts[1].toInt()
        }
        return intArrayOf(hours, minutes)
    }

    private fun checkTimeValues(values: List<Int>) = values.filter { it == 0 }.isEmpty()

    private fun createNotification(text: String?, id: Int) {
        val mBuilder = NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_replay)
                .setContentTitle(getString(R.string.notification_title))
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVibrate(longArrayOf(VIBRATION_TIME, VIBRATION_TIME, VIBRATION_TIME, VIBRATION_TIME))
                .setLights(Color.RED, BLINKING_TIME, BLINKING_TIME)
        val resultIntent = Intent(this, DisplayTaskActivity::class.java)

        val stackBuilder = TaskStackBuilder.create(this)
        stackBuilder.addNextIntent(resultIntent)
        val resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        mBuilder.setContentIntent(resultPendingIntent)
        val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mNotificationManager.notify(id, mBuilder.build())
    }

}
