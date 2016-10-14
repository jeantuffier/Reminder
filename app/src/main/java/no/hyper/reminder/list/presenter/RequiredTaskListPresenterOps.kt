package no.hyper.reminder.list.presenter

import android.content.Context

/**
 * Created by Jean on 10/12/2016.
 */
interface RequiredTaskListPresenterOps {
    fun getApplicationContext() : Context
    fun getActivityContext() : Context
}