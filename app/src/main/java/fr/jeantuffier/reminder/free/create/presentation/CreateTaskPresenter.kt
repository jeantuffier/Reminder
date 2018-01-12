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
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class CreateTaskPresenter(private val view: CreateTaskContract.View, private val taskDao: TaskDao) : AbstractConnectionObserver(), CreateTaskContract.Presenter {

    private var taskId = ""

    private var dnsLocalService: DisplayNotificationService.LocalBinder? = null

    override fun createTask(context: Context, title: String, delay: Int, frequency: String, priorityForm: Int, time: Array<String>) {
        val priority = getPriority(priorityForm).getLevel()
        val createdAt = Calendar.getInstance().timeInMillis.toString()
        taskDao.getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .filter { it.isEmpty() }
                .subscribeOn(Schedulers.io())
                .subscribe {
                    val id = it.map { it.id }.max() ?: 0
                    val task = Task(id, title, priority, delay, frequency, time[0], time[1], createdAt)
                    saveTask(context, task)
                    registerAlarm(context)
                }
    }

    override fun onObserverConnected(className: ComponentName, service: IBinder) {
        dnsLocalService = service as DisplayNotificationService.LocalBinder
    }

    override fun onObserverDisconnected(className: ComponentName) = Unit

    private fun getPriority(priorityForm: Int?): Priority {
        return when (priorityForm) {
            0 -> Priority.LOW
            1 -> Priority.MIDDLE
            else -> Priority.HIGH
        }
    }

    private fun saveTask(context: Context, task: Task) {
        taskId = taskDao.insert(task).toString()
        view.notifyNewItem()
        val intent = Intent(context, DisplayNotificationService::class.java)
        context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    private fun registerAlarm(context: Context) {
        taskId.let { dnsLocalService?.service?.scheduleNewTask(it) }
        context.unbindService(serviceConnection)
    }

}
