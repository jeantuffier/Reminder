package fr.jeantuffier.reminder.free.create.view.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SeekBar
import fr.jeantuffier.reminder.R
import kotlinx.android.synthetic.main.create_task_fragment_mandatory.*

/**
 * Created by jean on 01.11.2016.
 */
class CreateTaskMandatoryFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.create_task_fragment_mandatory, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let { setFrequencySpinner(it) }
        setPriorityBar()
    }

    fun getTitle() = task_title.text.toString()

    fun getDelay() = task_delay.text.toString()

    fun getFrequency() = task_frequency.selectedItem.toString()

    fun getPriority() = task_priority.progress

    private fun setFrequencySpinner(context: Context) {
        val frequencies = arrayListOf(getString(R.string.create_task_frequency_hours),
                getString(R.string.create_task_frequency_minutes))
        val adapter =  ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, frequencies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        task_frequency.adapter = adapter
        task_frequency.setSelection(0)
    }

    private fun setPriorityBar() {
        val priority = getString(R.string.create_task_priority,
                getString(R.string.create_task_priority_middle).toLowerCase())
        task_priority_progress.text = priority
        task_priority.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(seekBar: SeekBar?) = Unit
            override fun onStopTrackingTouch(seekBar: SeekBar?) = Unit
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                task_priority_progress.text = when (progress) {
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

}