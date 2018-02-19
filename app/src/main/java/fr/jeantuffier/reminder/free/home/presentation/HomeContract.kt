package fr.jeantuffier.reminder.free.home.presentation

import fr.jeantuffier.reminder.free.common.model.Task

class HomeContract {

    interface View {
        fun setTasks(tasks: List<Task>)
        fun deleteTask(position: Int)
    }

    interface Presenter {
        fun loadData()
    }

}
