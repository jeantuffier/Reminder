package fr.jeantuffier.reminder.free.create.model

import fr.jeantuffier.reminder.free.common.model.Task

/**
 * Created by jean on 08.11.2016.
 */
interface ProvidedCreateTaskModelOps {
    fun saveNewTask(task: Task) : Long?
    fun getTaskCount() : Int
    fun getHighestTaskId() : Int?
}