package fr.jeantuffier.reminder.free.create.presentation

import android.content.ComponentName
import android.content.Context
import android.os.IBinder
import fr.jeantuffier.reminder.free.common.model.Priority
import fr.jeantuffier.reminder.free.common.model.Task
import fr.jeantuffier.reminder.free.common.service.AbstractConnectionObserver
import fr.jeantuffier.reminder.free.common.service.DisplayNotificationService
import java.util.*
import javax.inject.Inject

class CreateTaskPresenter @Inject constructor(
        private val context: Context,
        private val view: CreateTaskContract.View
) : AbstractConnectionObserver(), CreateTaskContract.Presenter {

    //@Inject
    //lateinit var taskDao: TaskDao

    private var taskId = ""

    private var dnsLocalService: DisplayNotificationService.LocalBinder? = null

    override fun createTask(title: String, delay: Int, frequency: String, priorityForm: Int, time: Array<String>) {
        val priority = getPriority(priorityForm).getLevel()
        val createdAt = Calendar.getInstance().timeInMillis.toString()
        /*taskDao.getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .filter { it.isEmpty() }
                .subscribeOn(Schedulers.io())
                .subscribe {
                    val id = it.map { it.id }.max() ?: 0
                    val task = Task(id, title, priority, delay, frequency, time[0], time[1], createdAt)
                    saveTask(task)
                }*/
    }

    override fun onObserverConnected(className: ComponentName, service: IBinder) {
        dnsLocalService = service as DisplayNotificationService.LocalBinder
        registerAlarm()
    }

    override fun onObserverDisconnected(className: ComponentName) = Unit

    private fun getPriority(priorityForm: Int?): Priority {
        return when (priorityForm) {
            0 -> Priority.LOW
            1 -> Priority.MIDDLE
            else -> Priority.HIGH
        }
    }

    private fun saveTask(task: Task) {
        /*taskDao.insert(task)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe {
                    view.notifyNewItem()
                    val intent = Intent(context, DisplayNotificationService::class.java)
                    context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
                }*/
    }

    private fun registerAlarm() {
        taskId.let { dnsLocalService?.service?.scheduleNewTask(it) }
        context.unbindService(serviceConnection)
    }

}
