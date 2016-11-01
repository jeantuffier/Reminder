package no.hyper.reminder.create.presenter

import android.support.v4.app.Fragment
import no.hyper.reminder.create.view.activity.RequiredCreateTaskViewOps
import no.hyper.reminder.create.view.fragment.CreateTaskFrequencyFragment
import no.hyper.reminder.create.view.fragment.CreateTaskNameFragment
import no.hyper.reminder.create.view.fragment.CreateTaskPriorityFragment

/**
 * Created by jean on 01.11.2016.
 */

class CreateTaskPresenter(private val view: RequiredCreateTaskViewOps) : ProvidedCreateTaskListPresenterOps {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> CreateTaskNameFragment()
            1 -> CreateTaskFrequencyFragment()
            else -> CreateTaskPriorityFragment()
        }
    }

    override fun getCount() = 3

}
