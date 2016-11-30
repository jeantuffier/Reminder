package fr.jeantuffier.reminder.create.view.fragment.picker

import android.app.Dialog
import android.app.DialogFragment
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.TimePicker
import fr.jeantuffier.reminder.create.view.fragment.DateTimePickerListener
import java.util.*

/**
 * Created by jean on 30.11.2016.
 */

class CreateTaskTimePicker : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        return TimePickerDialog(activity, this, hour, minute, DateFormat.is24HourFormat(activity))
    }

    override fun onTimeSet(p0: TimePicker?, hour: Int, minute: Int) {
        activity?.let {
            val time = "$hour:$minute"
            (it as DateTimePickerListener).onTimePicked(time)
        }
    }

}