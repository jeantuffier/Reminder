package fr.jeantuffier.reminder.create.view.activity

import android.content.Context
import fr.jeantuffier.reminder.common.model.Task

/**
 * Created by jean on 01.11.2016.
 */
interface RequiredCreateTaskViewOps {
    fun getTaskTitle() : String?
    fun getTaskFrequencyDelay() : Int?
    fun getTaskFrequencyType() : Int?
    fun getTaskPriority() : Int

    fun getResourceString(id: Int) : String
    fun getActivityContext() : Context?
    fun getApplicationContext() : Context?

    fun notifyNewItem()
    fun error(message: String)
}