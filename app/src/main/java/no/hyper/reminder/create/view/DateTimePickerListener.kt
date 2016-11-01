package no.hyper.reminder.create.view

/**
 * Created by jean on 01.11.2016.
 */
interface DateTimePickerListener {
    fun onDatePicked(date: String)
    fun onTimePicked(time: String)
}