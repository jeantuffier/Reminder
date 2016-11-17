package no.hyper.reminder.create.view.activity

import android.content.Context
import no.hyper.reminder.common.model.task.Task

/**
 * Created by jean on 01.11.2016.
 */
interface RequiredCreateTaskViewOps {
    fun getTaskTitle() : String?
    fun getTaskFrequencyDelay() : String?
    fun getTaskFrequencyType() : String?
    fun getTaskPriority() : Int

    fun getResourceString(id: Int) : String
    fun getActivityContext() : Context?
    fun getApplicationContext() : Context?

    fun notifyNewItem()
    fun error(message: String)
}