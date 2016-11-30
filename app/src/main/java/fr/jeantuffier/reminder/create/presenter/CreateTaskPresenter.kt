package fr.jeantuffier.reminder.create.presenter

import fr.jeantuffier.reminder.R
import fr.jeantuffier.reminder.common.model.task.Task
import fr.jeantuffier.reminder.common.model.task.regular.RegularTask
import fr.jeantuffier.reminder.common.model.timer.Timer
import fr.jeantuffier.reminder.create.model.ProvidedCreateTaskModelOps
import fr.jeantuffier.reminder.create.view.activity.RequiredCreateTaskViewOps
import java.lang.ref.WeakReference
import java.util.*
import fr.jeantuffier.reminder.common.jobscheduler.JobManager


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

        JobManager.registerNewJob(context, task)
    }

}
