package no.hyper.reminder.create.presenter.service

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.app.PendingIntent
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.support.v4.app.NotificationCompat
import android.support.v4.app.TaskStackBuilder
import no.hyper.reminder.R
import no.hyper.reminder.common.model.task.Task
import no.hyper.reminder.display.view.DisplayTaskActivity
import java.util.concurrent.TimeUnit


/**
 * Created by jean on 21.11.2016.
 */
class DisplayNotificationService : JobService() {

    override fun onStartJob(params: JobParameters?): Boolean {

        val text = params?.extras?.getString(Task.TITLE)
        val vb = TimeUnit.SECONDS.toMillis(1)

        val mBuilder = NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_replay)
                .setContentTitle(getString(R.string.notification_title))
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVibrate(longArrayOf(vb, vb, vb, vb))
                .setLights(Color.RED, 3000, 3000)
        val resultIntent = Intent(this, DisplayTaskActivity::class.java)

        val stackBuilder = TaskStackBuilder.create(this)
        stackBuilder.addNextIntent(resultIntent)
        val resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        mBuilder.setContentIntent(resultPendingIntent)
        val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mNotificationManager.notify(0, mBuilder.build())

        jobFinished(params, false)
        return true
    }

    override fun onStopJob(params: JobParameters?) = true

}