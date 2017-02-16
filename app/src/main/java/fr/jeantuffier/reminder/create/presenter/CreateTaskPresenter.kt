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
        val task = Task(UUID.randomUUID().toString(), title, priority, delay.toInt(), frequency, time[0] ?: "",
                time[1] ?: "")

        val rowId = model.saveNewTask(task)
        if (rowId != null) {
            registerAlarm(task)
            view.get()?.notifyNewItem()
        }

    }

    override fun getActivityContext() = view.get()?.getActivityContext()

    override fun getApplicationContext() = view.get()?.getApplicationContext()

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

    private fun registerAlarm(task: Task) {
        val context = view.get()?.getActivityContext()
        context ?: return

        JobManager.registerNewJob(context, task)
    }

}
