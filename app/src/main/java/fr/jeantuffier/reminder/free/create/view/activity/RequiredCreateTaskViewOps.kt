package fr.jeantuffier.reminder.free.create.view.activity

import android.content.Context

/**
 * Created by jean on 01.11.2016.
 */
interface RequiredCreateTaskViewOps {
    fun getTaskTitle() : String
    fun getDelay() : String
    fun getFrequency() : String
    fun getPriority() : Int
    fun getTaskTime() : Array<String?>

    fun getResourceString(id: Int) : String
    fun getActivityContext() : Context?
    fun getApplicationContext() : Context?

    fun notifyNewItem()
    fun error(message: String)
}