package fr.jeantuffier.reminder.common.extension

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable

/**
 * Created by jean on 08.11.2016.
 */

fun Intent.withExtras(vararg extras: Pair<String, Any>) : Intent {
    val bundle = Bundle()
    extras.forEach {
        val (key, value) = it
        when (value) {
            is Parcelable -> bundle.putParcelable(key, value)
            is Boolean -> bundle.putBoolean(key, value)
            is Int -> bundle.putInt(key, value)
            is Float -> bundle.putFloat(key, value)
            is String -> bundle.putString(key, value)
            else -> throw Exception("Bundle.with: $key to ${value.javaClass} not supported")
        }
    }
    this.putExtras(bundle)
    return this
}