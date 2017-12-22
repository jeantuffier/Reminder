package fr.jeantuffier.reminder.free.display.view

import android.content.Context

/**
 * Created by jean on 14.10.2016.
 */
interface RequiredDisplayTaskViewOps {
    fun getApplicationContext() : Context
    fun getActivityContext() : Context
    fun addLongItemClickMenuOptionsFor(position: Int)
}