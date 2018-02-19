package fr.jeantuffier.reminder.free.common

import android.app.AlarmManager
import android.app.PendingIntent
import android.arch.persistence.room.Room
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import fr.jeantuffier.reminder.free.common.dao.AppDatabase
import fr.jeantuffier.reminder.free.common.dao.TaskDao
import fr.jeantuffier.reminder.free.common.extension.getIntent
import fr.jeantuffier.reminder.free.common.extension.withExtras
import fr.jeantuffier.reminder.free.common.model.Priority
import fr.jeantuffier.reminder.free.common.model.Task
import fr.jeantuffier.reminder.free.common.service.DisplayNotificationService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

private const val BOOT_COMPLETED = "android.intent.action.BOOT_COMPLETED"

class BootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == BOOT_COMPLETED) {
            val appDatabase = getDatabase(context)
            getAlarms(context, appDatabase.taskDAO())
        }
    }

    private fun getDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DB_NAME
        ).build()
    }

    private fun getAlarms(context: Context, taskDao: TaskDao) {
        taskDao.getAll()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe { tasks ->
                for (task in tasks) {
                    setAlarm(context, task)
                }
            }
    }

    private fun setAlarm(context: Context, task: Task) {
        val triggerAt = System.currentTimeMillis() + task.getDelayInMs()
        val intent = getTaskIntent(context, task)
        val pendingIntent = PendingIntent.getService(context, task.id, intent, 0)

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setInexactRepeating(
            AlarmManager.RTC,
            triggerAt,
            task.getDelayInMs(),
            pendingIntent
        )
    }

    private fun getTaskIntent(context: Context, task: Task) =
        context.getIntent<DisplayNotificationService>()
            .withExtras {
                putInt(Task.ID, task.id)
                putString(Task.TITLE, task.title)
                putString(Task.PRIORITY, Priority.getByLevel(task.priority).toString())
                putString(Task.FROM, task.fromTime)
                putString(Task.TO, task.toTime)
            }
}