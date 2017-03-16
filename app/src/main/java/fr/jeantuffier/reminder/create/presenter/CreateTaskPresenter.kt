package fr.jeantuffier.reminder.create.presenter

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.IBinder
import fr.jeantuffier.reminder.R
import fr.jeantuffier.reminder.create.model.ProvidedCreateTaskModelOps
import fr.jeantuffier.reminder.create.view.activity.RequiredCreateTaskViewOps
import java.lang.ref.WeakReference
import fr.jeantuffier.reminder.common.model.Priority
import fr.jeantuffier.reminder.common.model.Task
import fr.jeantuffier.reminder.common.service.DisplayNotificationService
import fr.jeantuffier.reminder.common.service.ServiceConnectionObserver
import java.util.*

/**
 * Created by jean on 01.11.2016.
 */

class CreateTaskPresenter(val view: WeakReference<RequiredCreateTaskViewOps>) : ServiceConnectionObserver(),
        ProvidedCreateTaskPresenterOps, RequiredCreateTaskPresenterOps {

    lateinit var model : ProvidedCreateTaskModelOps
    private var taskId = ""

    private var dnsLocalService : DisplayNotificationService.LocalBinder? = null

    override fun createTask() {
        val title = view.get()?.getTaskTitle()
        val delay = view.get()?.getDelay()
        val frequency = view.get()?.getFrequency()
        val priorityForm = view.get()?.getPriority()
        val time = view.get()?.getTaskTime()

        if (title == null || title.isEmpty() ||
                delay == null || delay.isEmpty() ||
                frequency == null || delay.isEmpty() ||
                time == null || delay.isEmpty())  {
            showError()
            return
        }

        val priority = getPriority(priorityForm)
        val createdAt = Calendar.getInstance().timeInMillis.toString()

        taskId = ((model.getHighestTaskId() ?: 0) + 1).toString()
        val task = Task(taskId, title, priority, delay.toInt(), frequency, time[0] ?: "", time[1] ?: "", createdAt)
        model.saveNewTask(task)

        val context = view.get()?.getActivityContext()
        context ?: return

        val intent = Intent(context, DisplayNotificationService::class.java)
        context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)

        view.get()?.notifyNewItem()
    }

    override fun getActivityContext() = view.get()?.getActivityContext()

    override fun getApplicationContext() = view.get()?.getApplicationContext()

    override fun onObserverConnected(className: ComponentName, service: IBinder) {
        dnsLocalService = service as DisplayNotificationService.LocalBinder
        registerAlarm()
    }

    override fun onObserverDisconnected(className: ComponentName) = Unit

    private fun showError() {
        view.get()?.getResourceString(R.string.create_task_error_field_empty)?.let {
            view.get()?.error(it)
        }
    }

    private fun getPriority(priorityForm: Int?) : Priority {
        return when(priorityForm) {
            0 -> Priority.LOW
            1 -> Priority.MIDDLE
            else -> Priority.HIGH
        }
    }

    private fun registerAlarm() {
        getActivityContext()?.let {
            taskId.let { dnsLocalService?.service?.scheduleNewTask(it) }
            it.unbindService(serviceConnection)
        }
    }

}
