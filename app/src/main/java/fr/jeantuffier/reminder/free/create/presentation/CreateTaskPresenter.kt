package fr.jeantuffier.reminder.free.create.presentation

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.IBinder
import fr.jeantuffier.reminder.free.common.dao.TaskDao
import fr.jeantuffier.reminder.free.common.model.Priority
import fr.jeantuffier.reminder.free.common.model.Task
import fr.jeantuffier.reminder.free.common.service.AbstractConnectionObserver
import fr.jeantuffier.reminder.free.common.service.DisplayNotificationService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class CreateTaskPresenter(private val context: Context, private val view: CreateTaskContract.View, private val taskDao: TaskDao) : AbstractConnectionObserver(), CreateTaskContract.Presenter {

    private var task : Task? = null

    private var displayNotificationService: DisplayNotificationService.LocalBinder? = null

    override fun createTask(title: String, delay: Int, frequency: String, priorityForm: Int, time: Array<String>) {
        val priority = getPriority(priorityForm).getLevel()
        val createdAt = Calendar.getInstance().timeInMillis.toString()
        taskDao.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    task = Task(getNextTaskId(it), title, priority, delay, frequency, time[0], time[1], createdAt)
                    bindService()
                }
    }

    override fun onObserverConnected(className: ComponentName, service: IBinder) {
        displayNotificationService = service as DisplayNotificationService.LocalBinder
        if (task != null) {
            saveTask()
        }
    }

    override fun onObserverDisconnected(className: ComponentName) = Unit

    private fun bindService() {
        val intent = Intent(context, DisplayNotificationService::class.java)
        context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    private fun getPriority(priorityForm: Int?): Priority {
        return when (priorityForm) {
            0 -> Priority.LOW
            1 -> Priority.MIDDLE
            else -> Priority.HIGH
        }
    }

    private fun getNextTaskId(tasks: List<Task>) = (tasks.map { it.id }.max() ?: 0) + 1

    private fun saveTask() {
        task?.let { task ->
            Single.fromCallable { taskDao.insert(task) }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { id ->
                        registerAlarm(id.toString())
                        view.notifyNewItem()
                    }
        }
    }

    private fun registerAlarm(taskId: String) {
        displayNotificationService?.service?.scheduleNewTask(taskId)
        context.unbindService(serviceConnection)
    }

}
