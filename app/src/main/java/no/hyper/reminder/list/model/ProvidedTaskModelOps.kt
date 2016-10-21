package no.hyper.reminder.list.model

import no.hyper.reminder.common.model.Task
import no.hyper.reminder.common.model.regular.RegularTask

/**
 * Created by jean on 14.10.2016.
 */
interface ProvidedTaskModelOps {
    fun getTaskCount() : Int
    fun getTask(position: Int) : Task?
    fun saveTask(task: RegularTask) : Long
    fun loadData() : Boolean
}