package no.hyper.reminder.create.presenter

import android.content.Context
import no.hyper.reminder.common.model.task.Task

/**
 * Created by jean on 08.11.2016.
 */
interface RequiredCreateTaskPresenterOps {
    fun getActivityContext() : Context?
    fun notifyTaskCreated(task: Task)
}