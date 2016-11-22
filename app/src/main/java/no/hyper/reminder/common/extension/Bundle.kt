package no.hyper.reminder.common.extension

import android.os.PersistableBundle

/**
 * Created by jean on 22.11.2016.
 */

fun PersistableBundle.withExtras(vararg extras: Pair<String, Any>) : PersistableBundle {
    extras.forEach {
        val (key, value) = it
        when (value) {
            is String -> this.putString(key, value)
            else -> throw Exception("Bundle.with: $key to ${value.javaClass} not supported")
        }
    }
    return this
}