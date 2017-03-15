package fr.jeantuffier.reminder.display.model

import fr.jeantuffier.reminder.common.model.Task


/**
 * Created by jean on 14.10.2016.
 */
interface ProvidedDisplayTaskModelOps {
    fun createDatabase()
    fun getTaskCount() : Int
    fun getTask(position: Int) : Task?
    fun getPosition(task: Task) : Int?
    fun saveTask(task: Task) : Long
    fun loadData()
    fun deleteTask(position: Int)
}