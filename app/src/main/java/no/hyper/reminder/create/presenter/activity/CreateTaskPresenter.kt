package no.hyper.reminder.create.presenter.activity

import android.support.v4.app.Fragment
import no.hyper.reminder.create.view.activity.RequiredCreateTaskViewOps
import no.hyper.reminder.create.view.fragment.CreateTaskFrequencyFragment
import no.hyper.reminder.create.view.fragment.CreateTaskTitleFragment
import no.hyper.reminder.create.view.fragment.CreateTaskPriorityFragment

/**
 * Created by jean on 01.11.2016.
 */

class CreateTaskPresenter(private val view: RequiredCreateTaskViewOps) : ProvidedCreateTaskPresenterOps {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> CreateTaskTitleFragment()
            1 -> CreateTaskFrequencyFragment()
            else -> CreateTaskPriorityFragment()
        }
    }

    override fun getCount() = 3

    override fun createTask() {
        
    }
}
