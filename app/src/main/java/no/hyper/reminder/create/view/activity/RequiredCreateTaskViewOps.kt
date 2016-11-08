package no.hyper.reminder.create.view.activity

import android.content.Context

/**
 * Created by jean on 01.11.2016.
 */
interface RequiredCreateTaskViewOps {
    fun getTaskTitle() : String?
    fun getTaskFrequencyDelay() : String?
    fun getTaskFrequencyType() : String?
    fun getTaskPriority() : Int

    fun getResourceString(id: Int) : String
    fun getContext() : Context?

    fun notifyNewItem(position: Int)
    fun error(message: String)
}