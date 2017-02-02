package fr.jeantuffier.reminder.create.presenter

import android.content.Context
import fr.jeantuffier.reminder.common.model.Task

/**
 * Created by jean on 08.11.2016.
 */
interface RequiredCreateTaskPresenterOps {
    fun getActivityContext() : Context?
    fun getApplicationContext() : Context?
}