package no.hyper.reminder.create.presenter

import android.content.Context

/**
 * Created by jean on 08.11.2016.
 */
interface RequiredCreateTaskPresenterOps {
    fun getContext() : Context?
    fun notifyTaskCreated(position: Int)
}