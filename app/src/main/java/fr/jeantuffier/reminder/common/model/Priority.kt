package fr.jeantuffier.reminder.common.model

import fr.jeantuffier.reminder.R

/**
 * Created by jean on 02.02.2017.
 */

enum class Priority {

    LOW {
        override fun getLevel() = 1
        override fun getColorId() = R.color.colorPriorityLow
    },

    MIDDLE {
        override fun getLevel() = 2
        override fun getColorId() = R.color.colorPriorityMiddle
    },

    HIGH {
        override fun getLevel() = 3
        override fun getColorId() = R.color.colorPriorityHigh
    };

    abstract fun getLevel() : Int
    abstract fun getColorId() : Int

}