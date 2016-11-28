package fr.jeantuffier.reminder.common.extension

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by jean on 02.11.2016.
 */

fun Context.getInteger(resourceId: Int) = this.resources.getInteger(resourceId)

fun <T> Context.readPreference(function: (prefs: SharedPreferences) -> T) : T {
    val prefs = this.getSharedPreferences(this.packageName, android.content.Context.MODE_PRIVATE)
    return function(prefs)
}

fun Context.editPreferences(function: (editor: SharedPreferences.Editor) -> Unit) {
    val prefs = this.getSharedPreferences(this.packageName, android.content.Context.MODE_PRIVATE)
    val editor = prefs.edit()
    function(editor)
    editor.apply()
}