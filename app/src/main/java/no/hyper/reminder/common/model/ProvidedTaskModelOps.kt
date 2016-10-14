package no.hyper.reminder.common.model

import no.hyper.reminder.common.model.regular.RegularTask

/**
 * Created by jean on 14.10.2016.
 */
interface ProvidedTaskModelOps {
    fun getTaskCount() : Int
    fun getTask(position: Int) : Task
    fun insertTask(task: RegularTask)
    fun loadData() : Boolean
}