package fr.jeantuffier.reminder.free.common.extension

import android.os.Bundle

fun Bundle.withExtras(vararg extras: Pair<String, Any>) : Bundle {
    extras.forEach {
        val (key, value) = it
        when (value) {
            is String -> this.putString(key, value)
            else -> throw Exception("Bundle.with: $key to ${value.javaClass} not supported")
        }
    }
    return this
}