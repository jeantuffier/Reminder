package fr.jeantuffier.mom.display.presenter

import android.content.Context

/**
 * Created by Jean on 10/12/2016.
 */
interface RequiredDisplayTaskPresenterOps {
    fun getApplicationContext() : Context?
    fun getActivityContext() : Context?
}