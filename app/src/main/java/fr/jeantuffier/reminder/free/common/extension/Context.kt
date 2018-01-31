package fr.jeantuffier.reminder.free.common.extension

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences

fun Context.getInteger(resourceId: Int) = this.resources.getInteger(resourceId)

fun <T> Context.readPreference(function: SharedPreferences.() -> T): T {
    val prefs = getSharedPreferences(this.packageName, android.content.Context.MODE_PRIVATE)
    return function(prefs)
}

fun Context.editPreferences(function: SharedPreferences.Editor.() -> Unit) {
    val prefs = getSharedPreferences(this.packageName, android.content.Context.MODE_PRIVATE)
    val editor = prefs.edit()
    function(editor)
    editor.apply()
}

inline fun <reified T> Context.getIntent() : Intent {
    return Intent(this, T::class.java)
}