package no.hyper.reminder.common.model.task

import android.os.Parcelable
import no.hyper.reminder.R
import no.hyper.reminder.common.model.timer.Timer

/**
 * Created by jean on 14.10.2016.
 */
interface Task : Parcelable {

    companion object {
        val CREATED = 1
        val PARCELABLE = "Task.PARCELABLE"
    }

    fun getViewType(factory: ViewTypeFactory) : Int?
    fun getTitle() : String
    fun getPriority() : Priority
    fun getTimer() : Timer

}