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

    private val FROM = "CreateTaskTimeFragment.FROM"
    private val TO = "CreateTaskTimeFragment.TO"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.create_task_fragment_time, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        setListener(from_time?.editText, FROM)
        setListener(to_time?.editText, TO)
    }

    override fun onDatePicked(date: String, tag: String) {
        showTimePickerDialog(tag)
    }

    override fun onTimePicked(time: String, tag: String) {
        if (tag == FROM) {
            from_time.editText?.setText(time)
        } else {
            to_time.editText?.setText(time)
        }
    }

    private fun setListener(view: View?, tag: String) {
        view?.setOnFocusChangeListener { view, focused ->
            if (focused) {
                showTimePickerDialog(tag)
            }
        }
        view?.setOnClickListener {
            showTimePickerDialog(tag)
        }
    }

    private fun showDatePickerDialog(tag: String) {
        val newFragment = CreateTaskDatePicker(WeakReference(this))
        newFragment.show(fragmentManager, tag)
    }

    private fun showTimePickerDialog(tag: String) {
        val newFragment = CreateTaskTimePicker(WeakReference(this))
        newFragment.show(fragmentManager, tag)
    }

}