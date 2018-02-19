package fr.jeantuffier.reminder.free.common.model

enum class Frequency {

    MINUTES {
        override fun getTimeInMs(time: Int) = (time * 60 * 1000).toLong()
    },
    HOURS {
        override fun getTimeInMs(time: Int) = (time * 60 * 60 * 1000).toLong()
    };

    override fun toString() = this.name.toLowerCase()
    abstract fun getTimeInMs(time: Int): Long

}