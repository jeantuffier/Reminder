package fr.jeantuffier.reminder.common.services

import android.content.Intent
import android.app.PendingIntent
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.graphics.Color
import android.os.*
import android.support.v4.app.NotificationCompat
import android.support.v4.app.TaskStackBuilder
import android.util.Log
import fr.jeantuffier.reminder.R
import fr.jeantuffier.reminder.common.model.Task
import fr.jeantuffier.reminder.display.view.DisplayTaskActivity
import no.hyper.memoryorm.Memory
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.ScheduledThreadPoolExecutor
import java.util.concurrent.TimeUnit
import android.os.Looper
import android.os.HandlerThread

/**
 * Created by jean on 21.11.2016.
 */
class DisplayNotificationService : Service() {

    companion object {
        var isRunning = false
        val ACTION = "DisplayNotificationService.ACTION"
        val SCHEDULE_NEW_TASK = "DisplayNotificationService.SCHEDULE_NEW_TASK"
        val DELETE_EXISTING_TASK = "DisplayNotificationService.DELETE_EXISTING_TASK"
    }

    private val THREAD_NUMBER = 5
    private val VIBRATION_TIME : Long = 250
    private val BLINKING_TIME = 3000

    private val sch = Executors.newScheduledThreadPool(THREAD_NUMBER) as ScheduledThreadPoolExecutor
    private val futurePool = hashMapOf<String, ScheduledFuture<*>>()
    private val localBinder = LocalBinder()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        isRunning = true
        Log.d("DNS", "onStartCommand done")

        Memory(this).fetchAll(Task::class.java)?.forEach {
            createFuture(it, 10)
        }

        return START_STICKY
    }

    override fun onBind(intent: Intent?) = localBinder

    override fun onDestroy() {
        Log.d("DNS", "Service done")
        isRunning = false
    }

    inner class LocalBinder : Binder() {
        val service: DisplayNotificationService
            get() = this@DisplayNotificationService
    }

    fun scheduleNewTask(taskId: String) {
        super.onCreate()
        Log.e("DNS", "DisplayNotificationService started")

        Memory(this).fetchById(Task::class.java, taskId)?.let { createFuture(it, null) }
    }

    fun deleteExistingTask(taskId: String) {
        futurePool[taskId]?.get()?.let {
            sch.remove(it as Runnable)
        }
        futurePool.remove(taskId)
    }

    private fun createFuture(task: Task, recreationDelay: Long?) {
        val unit = if (task.frequency == Task.HOURS) TimeUnit.HOURS else TimeUnit.MINUTES
        val runnable = Runnable { verifyData(task) }
        val future = sch.scheduleAtFixedRate(runnable, recreationDelay ?: 3, 3, TimeUnit.SECONDS)//task.delay.toLong(), task.delay.toLong(), unit)
        futurePool.put(task.id, future)
    }

    private fun verifyData(task: Task) {
        val fromValues = getTimeValues(task.fromTime)
        val toValues = getTimeValues(task.toTime)

        val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val currentMinute = Calendar.getInstance().get(Calendar.MINUTE)

        if (checkTimeValues(listOf(fromValues[0], fromValues[1], toValues[0], toValues[1]))) {
            val isOverMinimal = currentHour > fromValues[0]
                    || (currentHour == fromValues[0] && currentMinute >= fromValues[1])
            val isUnderMaximal = currentHour < toValues[0]
                    || (currentHour == toValues[1] && currentMinute <= toValues[1])
            if (isOverMinimal && isUnderMaximal) {
                createNotification(task.title, 0)
            }
        } else {
            createNotification(task.title, 0)
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
