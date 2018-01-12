package fr.jeantuffier.reminder.free.create.presentation

import android.content.Context

class CreateTaskContract {

    interface View {
        fun notifyNewItem()
    }

    interface Presenter {
        fun createTask(context: Context, title: String, delay: Int, frequency: String, priorityForm: Int, time: Array<String>)
    }
}