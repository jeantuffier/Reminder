package fr.jeantuffier.reminder.free.home.presentation

import android.content.Context
import fr.jeantuffier.reminder.free.common.model.Task

class HomeContract {

    interface View {
        fun setTasks(tasks: List<Task>)
    }

    interface Presenter {
        fun onDbCreated(context: Context)
        fun loadData(context: Context)
        fun deleteItem(id: Int)
    }
}