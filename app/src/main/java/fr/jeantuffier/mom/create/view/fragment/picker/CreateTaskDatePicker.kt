package fr.jeantuffier.mom.create.view.fragment.picker

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.text.format.DateFormat
import android.widget.DatePicker
import java.lang.ref.WeakReference
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by jean on 30.11.2016.
 */

class CreateTaskDatePicker(private val listener: WeakReference<DateTimePickerListener>) : DialogFragment(),
        DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(activity, this, year, month, day)
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
        activity?.let {
            val stringDate = "${toStringDay(day)}/${toStringMonth(month)}/$year"
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val date = sdf.parse(stringDate)
            val dateFormat = DateFormat.getDateFormat(it)
            listener.get()?.onDatePicked(dateFormat.format(date), tag)
        }
    }

    private fun toStringDay(day: Int) = if (day < 10) "0$day" else day.toString()

    private fun toStringMonth(month: Int) = if (month + 1 < 10) "0${month + 1}" else (month + 1).toString()

}