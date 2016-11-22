package no.hyper.reminder.create.presenter

import android.content.Context
import no.hyper.reminder.R
import no.hyper.reminder.common.model.task.Task
import no.hyper.reminder.common.model.task.regular.RegularTask
import no.hyper.reminder.common.model.timer.Timer
import no.hyper.reminder.create.model.ProvidedCreateTaskModelOps
import no.hyper.reminder.create.presenter.service.DisplayNotificationService
import no.hyper.reminder.create.view.activity.RequiredCreateTaskViewOps
import java.lang.ref.WeakReference
import java.util.*
import android.content.ComponentName
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.os.Bundle
import android.os.PersistableBundle
import no.hyper.reminder.common.extension.withExtras
import java.util.concurrent.TimeUnit


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
                registerAlarm(task)
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

    private fun registerAlarm(task: Task) {
        val context = viewReference.get()?.getActivityContext()
        context ?: return

        val componentName = ComponentName(context, DisplayNotificationService::class.java)
        val builder = JobInfo.Builder(1, componentName)

        val delay = task.getTimer().delay
        val frequency = task.getTimer().frequency
        val period = if (frequency == Timer.Frequency.HOURS) {
            TimeUnit.HOURS.toMillis(delay.toLong())
        } else {
            TimeUnit.MINUTES.toMillis(delay.toLong())
        }

        builder.setPeriodic(period)
        builder.setRequiresDeviceIdle(false)
        builder.setRequiresCharging(false)

        val bundle = PersistableBundle().withExtras(Task.TITLE to task.getTitle())
        builder.setExtras(bundle)

        val jobScheduler = context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        jobScheduler.schedule(builder.build())
    }

}
