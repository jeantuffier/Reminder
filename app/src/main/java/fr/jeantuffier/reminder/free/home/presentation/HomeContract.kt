package fr.jeantuffier.reminder.free.home.presentation

import fr.jeantuffier.reminder.free.common.model.Task

class HomeContract {

    interface View {
        fun setTasks(tasks: List<Task>)
    }

    interface Presenter {
        fun onDbCreated()
        fun loadData()
        fun deleteItem(id: Int)
    }
}