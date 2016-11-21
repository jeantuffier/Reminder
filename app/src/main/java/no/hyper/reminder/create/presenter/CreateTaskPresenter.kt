package no.hyper.reminder.create.presenter

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import no.hyper.reminder.R
import no.hyper.reminder.common.model.task.Task
import no.hyper.reminder.common.model.task.regular.RegularTask
import no.hyper.reminder.common.model.timer.Timer
import no.hyper.reminder.create.model.ProvidedCreateTaskModelOps
import no.hyper.reminder.create.presenter.service.DisplayNotificationService
import no.hyper.reminder.create.view.activity.RequiredCreateTaskViewOps
import java.lang.ref.WeakReference
import java.util.UUID

/**
 * Created by jean on 01.11.2016.
 */

class CreateTaskPresenter(view: RequiredCreateTaskViewOps) : ProvidedCreateTaskPresenterOps,
        RequiredCreateTaskPresenterOps {

    private val LOG_TAG = this.javaClass.simpleName
    private var viewReference : WeakReference<RequiredCreateTaskViewOps>
    lateinit var model : ProvidedCreateTaskModelOps

    init { viewReference = WeakReference(view) }

    override fun createTask() {
        val title = viewReference.get()?.getTaskTitle()
        val delay = viewReference.get()?.getTaskFrequencyDelay()
        val typeForm = viewReference.get()?.getTaskFrequencyType()
        val priorityForm = viewReference.get()?.getTaskPriority()

        if (title != null && delay != null && typeForm != null) {
            val priority = getPriority(priorityForm)
            val type = Timer.Frequency.valueOf(typeForm.toUpperCase())

            val timer = Timer(UUID.randomUUID().toString(), type, delay.toInt())
            val task = RegularTask(UUID.randomUUID().toString(), title, priority, timer)

            val rowId = model.saveNewTask(task)
            if (rowId != null) {
                registerAlarm()
                viewReference.get()?.notifyNewItem()
            }

        } else {
            viewReference.get()?.getResourceString(R.string.create_task_error_field_empty)?.let {
                viewReference.get()?.error(it)
            }
        }

    }

    override fun getActivityContext() = viewReference.get()?.getActivityContext()

    override fun getApplicationContext() = viewReference.get()?.getApplicationContext()

    private fun getPriority(priorityForm: Int?) : Task.Priority {
        return when(priorityForm) {
            0 -> Task.Priority.LOW
            1 -> Task.Priority.MIDDLE
            else -> Task.Priority.HIGH
        }
    }

    private fun registerAlarm() {
        val context = viewReference.get()?.getActivityContext()
        val service = context?.getSystemService(Context.ALARM_SERVICE)

        context ?: return
        service ?: return

        val alarmManager = service as AlarmManager
        val ct = System.currentTimeMillis()
        val intent = Intent(context, DisplayNotificationService::class.java)
        val code = context.resources.getInteger(R.integer.request_intent_service_notification)
        val pendingIntent = PendingIntent.getService(context, code, intent, PendingIntent.FLAG_ONE_SHOT)

        alarmManager.set(AlarmManager.RTC, ct+4*1000, pendingIntent)
    }

}
