package fr.jeantuffier.reminder.create.model

import fr.jeantuffier.reminder.common.model.task.Task

/**
 * Created by jean on 08.11.2016.
 */
interface ProvidedCreateTaskModelOps {
    fun saveNewTask(task: Task) : Long?
    fun getTaskCount() : Int
}