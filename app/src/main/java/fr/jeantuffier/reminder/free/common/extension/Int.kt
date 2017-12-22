package fr.jeantuffier.reminder.free.common.extension

import android.content.Context
import android.util.TypedValue

/**
 * Created by Jean on 10/26/2016.
 */

fun Int.toDp(context: Context) : Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(),
            context.resources.displayMetrics).toInt()
}
