package fr.jeantuffier.reminder.free.home.presentation

import fr.jeantuffier.reminder.free.common.model.Task

class HomeModel(private val presenter: HomeContract.Presenter) : HomeContract.Model {

    override fun createDatabase() {

    }

    override fun saveTask(task: Task) = 0.toLong()

    override fun loadData() = listOf<Task>()

    override fun deleteTask(id: String) = Unit

}