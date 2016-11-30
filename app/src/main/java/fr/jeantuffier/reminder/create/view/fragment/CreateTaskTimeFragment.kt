package fr.jeantuffier.reminder.create.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fr.jeantuffier.reminder.R
import fr.jeantuffier.reminder.create.view.fragment.picker.CreateTaskDatePicker
import fr.jeantuffier.reminder.create.view.fragment.picker.CreateTaskTimePicker
import fr.jeantuffier.reminder.create.view.fragment.picker.DateTimePickerListener
import kotlinx.android.synthetic.main.create_task_fragment_time.*
import java.lang.ref.WeakReference

/**
 * Created by jean on 30.11.2016.
 */

class CreateTaskTimeFragment : Fragment(), DateTimePickerListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.create_task_fragment_time, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        setListener(from_time?.editText)
        setListener(to_time?.editText)
    }

    override fun onDatePicked(date: String) {
        showTimePickerDialog()
    }

    override fun onTimePicked(time: String) {

    }

    private fun setListener(view: View?) {
        view?.setOnFocusChangeListener { view, focused ->
            if (focused) {
                showDatePickerDialog()
            }
        }
        view?.setOnClickListener {
            showDatePickerDialog()
        }
    }

    private fun showDatePickerDialog() {
        val newFragment = CreateTaskDatePicker(WeakReference(this))
        newFragment.show(fragmentManager, "datePicker")
    }

    private fun showTimePickerDialog() {
        val newFragment = CreateTaskTimePicker(WeakReference(this))
        newFragment.show(fragmentManager, "timePicker")
    }

}