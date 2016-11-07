package no.hyper.reminder.create.presenter

import no.hyper.reminder.R
import no.hyper.reminder.common.model.task.Task
import no.hyper.reminder.common.model.task.regular.RegularTask
import no.hyper.reminder.common.model.timer.Timer
import no.hyper.reminder.create.view.activity.RequiredCreateTaskViewOps

/**
 * Created by jean on 01.11.2016.
 */

class CreateTaskPresenter(private val view: RequiredCreateTaskViewOps) : ProvidedCreateTaskPresenterOps {

    override fun createTask() {
        val title = view.getTaskTitle()
        val delay = view.getTaskFrequencyDelay()
        val typeForm = view.getTaskFrequencyType()
        val priorityForm = view.getTaskPriority()

        if (title != null && delay != null && typeForm != null) {
            val priority = getPriority(priorityForm)
            val type = Timer.Frequency.valueOf(typeForm)
            val task = RegularTask(title, priority, Timer(type, delay.toInt()))
            view.notifyNewItem(task)
        } else {
            view.error(view.getResourceString(R.string.create_task_error_field_empty))
        }

    }

    private fun getPriority(priorityForm: Int) : Task.Priority {
        return when(priorityForm) {
            0 -> Task.Priority.LOW
            1 -> Task.Priority.MIDDLE
            else -> Task.Priority.HIGH
        }
    }

}
