package no.hyper.reminder.common.model.task

import no.hyper.reminder.R


/**
 * Created by Jean on 10/26/2016.
 */
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