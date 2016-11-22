package no.hyper.reminder.display.model

import no.hyper.reminder.common.model.task.Task
import no.hyper.reminder.common.model.task.regular.RegularTask

/**
 * Created by jean on 14.10.2016.
 */
interface ProvidedDisplayTaskModelOps {
    fun createDatabase()
    fun getTaskCount() : Int
    fun getTask(position: Int) : Task?
    fun saveTask(task: RegularTask) : Long
    fun loadData()
    fun deleteTask(taskId: Long)
}