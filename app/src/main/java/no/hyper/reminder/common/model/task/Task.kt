package no.hyper.reminder.common.model.task

import android.os.Parcelable
import no.hyper.reminder.R
import no.hyper.reminder.common.model.timer.Timer

/**
 * Created by jean on 14.10.2016.
 */
interface Task : Parcelable {

    companion object {
        val TITLE = "Task.TITLE"
    }

    enum class Priority {

        LOW {
            override fun getLevel() = LOW
            override fun getColorId() = R.color.colorPriorityLow
            override fun getIconId() = R.drawable.ic_priority_low
        },

        MIDDLE {
            override fun getLevel() = MIDDLE
            override fun getColorId() = R.color.colorPriorityMiddle
            override fun getIconId() = R.drawable.ic_priority_middle
        },

        HIGH {
            override fun getLevel() = LOW
            override fun getColorId() = R.color.colorPriorityHigh
            override fun getIconId() = R.drawable.ic_priority_high
        };

        abstract fun getLevel() : Priority
        abstract fun getColorId() : Int
        abstract fun getIconId() : Int

    }

    fun getId(): String
    fun getViewType(factory: ViewTypeFactory) : Int?
    fun getTitle() : String
    fun getPriority() : Priority
    fun getTimer() : Timer

}