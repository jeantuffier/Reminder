package fr.jeantuffier.reminder.create.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import kotlinx.android.synthetic.main.create_task_fragment_frequency.*
import fr.jeantuffier.reminder.R

/**
 * Created by jean on 01.11.2016.
 */
class CreateTaskFrequencyFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.create_task_fragment_frequency, container, false)
    }

    fun getFrequencyDelay() : Int = Integer.valueOf(create_task_frequency_delay.editText?.text?.toString())

    fun getFrequencyType() = group_frequency.checkedRadioButtonId

}
