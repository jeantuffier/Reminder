package fr.jeantuffier.reminder.common.model.task

import android.os.Parcelable
import fr.jeantuffier.reminder.R
import fr.jeantuffier.reminder.common.model.timer.Timer

/**
 * Created by jean on 14.10.2016.
 */
interface Task : Parcelable {

    companion object {
        val TITLE = "Task.TITLE"
        val ID = "Task.ID"
    }

    enum class Priority {

        LOW {
            override fun getLevel() = 1
            override fun getColorId() = R.color.colorPriorityLow
            override fun getIconId() = R.drawable.ic_priority_low
        },

        MIDDLE {
            override fun getLevel() = 2
            override fun getColorId() = R.color.colorPriorityMiddle
            override fun getIconId() = R.drawable.ic_priority_middle
        },

        HIGH {
            override fun getLevel() = 3
            override fun getColorId() = R.color.colorPriorityHigh
            override fun getIconId() = R.drawable.ic_priority_high
        };

        abstract fun getLevel() : Int
        abstract fun getColorId() : Int
        abstract fun getIconId() : Int

    }

    fun getId(): String
    fun getViewType(factory: ViewTypeFactory) : Int?
    fun getTitle() : String
    fun getPriority() : Priority
    fun getTimer() : Timer

}