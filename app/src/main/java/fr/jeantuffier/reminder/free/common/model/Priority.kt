package fr.jeantuffier.reminder.free.common.model

import fr.jeantuffier.reminder.R

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

    companion object {
        fun getByLevel(level: Int) : Priority {
            return when(level) {
                1 -> LOW
                2 -> MIDDLE
                else -> HIGH
            }
        }
    }

    abstract fun getLevel() : Int
    abstract fun getColorId() : Int

}