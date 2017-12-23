package fr.jeantuffier.reminder.free.home.presentation

import fr.jeantuffier.reminder.free.common.model.Task

class HomeContract {

    interface Model {
        fun createDatabase()
        fun saveTask(task: Task): Long
        fun loadData() : List<Task>
        fun deleteTask(id: String)
    }

    interface View {
        fun setTasks(tasks: List<Task>)
    }

    interface Presenter {
        fun onDbCreated()
        fun loadData()
        fun deleteItem(id: String)
    }
}