package no.hyper.reminder.list.view

import android.content.Context

/**
 * Created by jean on 14.10.2016.
 */
interface RequiredTaskListViewOps {
    fun getApplicationContext() : Context
    fun getActivityContext() : Context
    fun notifyItemInserted()
}