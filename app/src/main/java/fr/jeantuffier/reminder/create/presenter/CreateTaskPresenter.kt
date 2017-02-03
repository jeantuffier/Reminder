package fr.jeantuffier.reminder.create.presenter

import fr.jeantuffier.reminder.R
import fr.jeantuffier.reminder.create.model.ProvidedCreateTaskModelOps
import fr.jeantuffier.reminder.create.view.activity.RequiredCreateTaskViewOps
import java.lang.ref.WeakReference
import java.util.*
import fr.jeantuffier.reminder.common.jobscheduler.JobManager
import fr.jeantuffier.reminder.common.model.Priority
import fr.jeantuffier.reminder.common.model.Task


/**
 * Created by jean on 01.11.2016.
 */

class CreateTaskPresenter(val view: WeakReference<RequiredCreateTaskViewOps>) : ProvidedCreateTaskPresenterOps,
        RequiredCreateTaskPresenterOps {

    lateinit var model : ProvidedCreateTaskModelOps

    override fun createTask() {
        val title = view.get()?.getTaskTitle()
        val delay = view.get()?.getTaskFrequencyDelay()
        val frequency = view.get()?.getTaskFrequencyType()
        val priorityForm = view.get()?.getTaskPriority()
        val time = view.get()?.getTaskTime()

        if (title != null && delay != null && frequency != null) {
            val priority = getPriority(priorityForm)

            val task = Task(UUID.randomUUID().toString(), title, priority, delay, frequency, time?.get(0) ?: "",
                    time?.get(1) ?: "")

            val rowId = model.saveNewTask(task)
            if (rowId != null) {
                registerAlarm(task)
                view.get()?.notifyNewItem()
            }

        } else {
            view.get()?.getResourceString(R.string.create_task_error_field_empty)?.let {
                view.get()?.error(it)
            }
        }

    }

    override fun getActivityContext() = view.get()?.getActivityContext()

    override fun getApplicationContext() = view.get()?.getApplicationContext()

    private fun getPriority(priorityForm: Int?) : Priority {
        return when(priorityForm) {
            0 -> Priority.LOW
            1 -> Priority.MIDDLE
            else -> Priority.HIGH
        }
    }

    private fun registerAlarm(task: Task) {
        val context = view.get()?.getActivityContext()
        context ?: return

        JobManager.registerNewJob(context, task)
    }

}
