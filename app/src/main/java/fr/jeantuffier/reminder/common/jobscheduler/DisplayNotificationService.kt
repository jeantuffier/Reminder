package fr.jeantuffier.reminder.common.jobscheduler

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.app.PendingIntent
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.support.v4.app.NotificationCompat
import android.support.v4.app.TaskStackBuilder
import fr.jeantuffier.reminder.R
import fr.jeantuffier.reminder.common.model.task.Task
import fr.jeantuffier.reminder.display.view.DisplayTaskActivity

/**
 * Created by jean on 21.11.2016.
 */
class DisplayNotificationService : JobService() {

    private val VIBRATION_TIME : Long = 250
    private val BLINKING_TIME = 3000

    override fun onStartJob(params: JobParameters): Boolean {
        val taskId = params.extras?.getString(Task.ID)
        if (!JobManager.isRegistered(this, taskId)) {
            JobManager.unregisterJob(this, taskId)
            jobFinished(params, false)
            return true
        }

        val jobId = params.jobId
        val text = params.extras?.getString(Task.TITLE)

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
        mNotificationManager.notify(jobId, mBuilder.build())

        jobFinished(params, false)
        return true
    }

    override fun onStopJob(params: JobParameters?) = true

}