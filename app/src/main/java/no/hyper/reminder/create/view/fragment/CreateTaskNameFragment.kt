package no.hyper.reminder.create.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import no.hyper.reminder.R

/**
 * Created by jean on 01.11.2016.
 */
class CreateTaskNameFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_create_task_name, container, false)
    }

}