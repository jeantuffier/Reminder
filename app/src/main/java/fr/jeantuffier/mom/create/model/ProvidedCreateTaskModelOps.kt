package fr.jeantuffier.mom.create.model

import fr.jeantuffier.mom.common.model.Task

/**
 * Created by jean on 08.11.2016.
 */
interface ProvidedCreateTaskModelOps {
    fun saveNewTask(task: Task) : Long?
    fun getTaskCount() : Int
    fun getHighestTaskId() : Int?
}