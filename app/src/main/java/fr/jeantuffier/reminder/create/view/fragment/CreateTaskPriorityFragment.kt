package fr.jeantuffier.reminder.create.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import kotlinx.android.synthetic.main.create_task_fragment_priority.*
import fr.jeantuffier.reminder.R

/**
 * Created by jean on 01.11.2016.
 */
class CreateTaskPriorityFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.create_task_fragment_priority, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        val priority = getString(R.string.create_task_priority,
                getString(R.string.create_task_priority_middle).toLowerCase())
        progress_priority_text.text = priority
        progress_priority.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(seekBar: SeekBar?) = Unit
            override fun onStopTrackingTouch(seekBar: SeekBar?) = Unit
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                progress_priority_text.text = when (progress) {
                    0 -> getString(R.string.create_task_priority,
                            getString(R.string.create_task_priority_low).toLowerCase())
                    1 -> getString(R.string.create_task_priority,
                            getString(R.string.create_task_priority_middle).toLowerCase())
                    else -> getString(R.string.create_task_priority,
                            getString(R.string.create_task_priority_high).toLowerCase())
                }
            }
        })
    }

    fun getTaskPriority() = progress_priority.progress

}