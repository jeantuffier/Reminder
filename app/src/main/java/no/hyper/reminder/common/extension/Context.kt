package no.hyper.reminder.common.extension

import android.content.Context

/**
 * Created by jean on 02.11.2016.
 */

fun Context.getScreenWidthInDp() : Int {
    val displayMetrics = resources.displayMetrics
    return (displayMetrics.widthPixels / displayMetrics.density).toInt()
}