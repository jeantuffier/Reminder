package fr.jeantuffier.reminder.free.create.presentation

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import fr.jeantuffier.reminder.free.common.dao.TaskDao
import fr.jeantuffier.reminder.free.common.extension.getIntent
import fr.jeantuffier.reminder.free.common.extension.withExtras
import fr.jeantuffier.reminder.free.common.model.Priority
import fr.jeantuffier.reminder.free.common.model.Task
import fr.jeantuffier.reminder.free.common.service.DisplayNotificationService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*


class CreateTaskPresenter(private val context: Context, private val view: CreateTaskContract.View, private val taskDao: TaskDao) : CreateTaskContract.Presenter {

    override fun createTask(title: String, delay: Int, frequency: String, priorityForm: Int, time: Array<String>) {
        taskDao.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { tasks ->
                    val priority = getPriority(priorityForm).getLevel()
                    val createdAt = Calendar.getInstance().timeInMillis.toString()
                    val task = Task(getNextTaskId(tasks), title, priority, delay, frequency, time[0], time[1], createdAt)
                    saveTask(task)
                }
    }

    private fun getPriority(priorityForm: Int?): Priority {
        return when (priorityForm) {
            0 -> Priority.LOW
            1 -> Priority.MIDDLE
            else -> Priority.HIGH
        }
    }

    private fun getNextTaskId(tasks: List<Task>) = (tasks.map { it.id }.max() ?: 0) + 1

    private fun saveTask(task: Task) {
        Single.fromCallable { taskDao.insert(task) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { id ->
                    view.notifyNewItem()
                    registerAlarm(task)
                }
    }

    private fun registerAlarm(task: Task) {
        val triggerAt = System.currentTimeMillis() + task.getDelayInMs()
        val intent = getTaskIntent(task)
        val pendingIntent = PendingIntent.getService(context, task.id, intent, 0)

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setInexactRepeating(AlarmManager.RTC, triggerAt, task.getDelayInMs(), pendingIntent)
    }

    private fun getTaskIntent(task: Task) = context.getIntent<DisplayNotificationService>()
            .withExtras {
                putInt(Task.ID, task.id)
                putString(Task.TITLE, task.title)
                putString(Task.PRIORITY, Priority.getByLevel(task.priority).toString())
                putString(Task.FROM, task.fromTime)
                putString(Task.TO, task.toTime)
            }

}
