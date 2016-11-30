package fr.jeantuffier.reminder.create.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.create_task_fragment_title.title_task
import fr.jeantuffier.reminder.R

/**
 * Created by jean on 01.11.2016.
 */
class CreateTaskTitleFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.create_task_fragment_title, container, false)
    }

    fun getTitle() = title_task.editText?.text?.toString()

}