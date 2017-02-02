package fr.jeantuffier.reminder.create.view.fragment.picker

/**
 * Created by jean on 30.11.2016.
 */

interface DateTimePickerListener {
    fun onDatePicked(date: String, tag: String)
    fun onTimePicked(time: String, tag: String)
}