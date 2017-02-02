package fr.jeantuffier.reminder.create.view.fragment.picker

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.text.format.DateFormat
import android.widget.TimePicker
import java.lang.ref.WeakReference
import java.util.*

/**
 * Created by jean on 30.11.2016.
 */

class CreateTaskTimePicker(private val listener: WeakReference<DateTimePickerListener>) : DialogFragment(),
        TimePickerDialog.OnTimeSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        return TimePickerDialog(activity, this, hour, minute, DateFormat.is24HourFormat(activity))
    }

    override fun onTimeSet(p0: TimePicker?, hour: Int, minute: Int) {
        activity?.let {
            val time = "$hour:$minute"
            listener.get()?.onTimePicked(time, tag)
        }
    }

}