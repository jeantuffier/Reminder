package no.hyper.reminder.common.model.task

import no.hyper.reminder.R

/**
 * Created by root on 15.11.16.
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

