package fr.jeantuffier.reminder.free.common.extension

import java.util.*

fun Calendar.setHour(value: Int) : Calendar {
    set(Calendar.HOUR_OF_DAY, value)
    return this
}

fun Calendar.setMinutes(value: Int) : Calendar {
    set(Calendar.MINUTE, value)
    return this
}