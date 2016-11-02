package no.hyper.reminder.create.presenter.activity

import android.support.v4.app.Fragment

/**
 * Created by jean on 01.11.2016.
 */
interface ProvidedCreateTaskPresenterOps {
    fun getItem(position: Int) : Fragment
    fun getCount() : Int
    fun createTask()
}