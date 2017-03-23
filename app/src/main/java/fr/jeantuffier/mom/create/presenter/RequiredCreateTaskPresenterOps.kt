package fr.jeantuffier.mom.create.presenter

import android.content.Context
import fr.jeantuffier.mom.common.model.Task

/**
 * Created by jean on 08.11.2016.
 */
interface RequiredCreateTaskPresenterOps {
    fun getActivityContext() : Context?
    fun getApplicationContext() : Context?
}