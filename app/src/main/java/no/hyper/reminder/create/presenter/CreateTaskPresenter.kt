package no.hyper.reminder.create.presenter

import no.hyper.reminder.R
import no.hyper.reminder.common.model.task.Task
import no.hyper.reminder.common.model.task.regular.RegularTask
import no.hyper.reminder.common.model.timer.Timer
import no.hyper.reminder.create.model.ProvidedCreateTaskModelOps
import no.hyper.reminder.create.view.activity.RequiredCreateTaskViewOps
import java.lang.ref.WeakReference

/**
 * Created by jean on 01.11.2016.
 */

class CreateTaskPresenter(view: RequiredCreateTaskViewOps) : ProvidedCreateTaskPresenterOps,
        RequiredCreateTaskPresenterOps {

    private var viewReference : WeakReference<RequiredCreateTaskViewOps>
    lateinit var model : ProvidedCreateTaskModelOps

    init {
        viewReference = WeakReference(view)
    }

    override fun createTask() {
        val title = viewReference.get()?.getTaskTitle()
        val delay = viewReference.get()?.getTaskFrequencyDelay()
        val typeForm = viewReference.get()?.getTaskFrequencyType()
        val priorityForm = viewReference.get()?.getTaskPriority()

        if (title != null && delay != null && typeForm != null) {
            val priority = getPriority(priorityForm)
            val type = Timer.Frequency.valueOf(typeForm.toUpperCase())
            val task = RegularTask(title, priority, Timer(type, delay.toInt()))

            val rowId = model.saveNewTask(task)
            if (rowId != null) {

            }

        } else {
            viewReference.get()?.getResourceString(R.string.create_task_error_field_empty)?.let {
                viewReference.get()?.error(it)
            }
        }

    }

    private fun getPriority(priorityForm: Int?) : Task.Priority {
        return when(priorityForm) {
            0 -> Task.Priority.LOW
            1 -> Task.Priority.MIDDLE
            else -> Task.Priority.HIGH
        }
    }

    override fun getContext() = viewReference.get()?.getContext()

    override fun notifyTaskCreated(position: Int) {
        viewReference.get()?.notifyNewItem(position)
    }

}
