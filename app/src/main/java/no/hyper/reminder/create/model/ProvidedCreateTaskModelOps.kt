package no.hyper.reminder.create.model

import no.hyper.reminder.common.model.task.Task

/**
 * Created by jean on 08.11.2016.
 */
interface ProvidedCreateTaskModelOps {
    fun saveNewTask(task: Task) : Int?
}