package fr.jeantuffier.reminder.common.services

import android.app.AlarmManager
import android.content.Context
import fr.jeantuffier.reminder.common.extension.readPreference
import fr.jeantuffier.reminder.common.model.Task
import java.util.concurrent.TimeUnit
import android.app.PendingIntent
import android.content.Intent
import android.os.SystemClock
import fr.jeantuffier.reminder.common.extension.withExtras


/**
 * Created by jean on 24.11.2016.
 */
object JobManager {

    fun registerNewJob(context: Context, task: Task) {
        val period = if (task.frequency == Task.HOURS) {
            TimeUnit.HOURS.toMillis(task.delay.toLong())
        } else {
            TimeUnit.MINUTES.toMillis(task.delay.toLong())
        }

        val manager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val alarmIntent = getPendingIntent(context, task)

        manager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + 3000, alarmIntent)
    }

    fun unregisterJob(context: Context, task: Task) {
        val manager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val alarmIntent = getPendingIntent(context, task)
        manager.cancel(alarmIntent)
    }

    fun isRegistered(context: Context, taskId: String?) : Boolean {
        val jobId = context.readPreference { it.getInt(context.packageName + taskId, -1) }
        return jobId != -1
    }

    private fun getPendingIntent(context: Context, task: Task) : PendingIntent {
        val intent = Intent(context, DisplayNotificationService::class.java)
                .withExtras(Task.ID to task.id, Task.TITLE to task.title,
                        Task.FROM to task.fromTime, Task.TO to task.toTime)
        return PendingIntent.getService(context, task.id.toInt(), intent, 0)
    }

}
