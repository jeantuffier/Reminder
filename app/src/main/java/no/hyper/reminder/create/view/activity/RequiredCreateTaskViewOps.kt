package no.hyper.reminder.create.view.activity

import no.hyper.reminder.common.model.task.Task

/**
 * Created by jean on 01.11.2016.
 */
interface RequiredCreateTaskViewOps {
    fun getTaskTitle() : String?
    fun getTaskFrequencyDelay() : String?
    fun getTaskFrequencyType() : String?
    fun getTaskPriority() : Int

    fun getResourceString(id: Int) : String
    fun notifyNewItem(task: Task)
    fun error(message: String)
}