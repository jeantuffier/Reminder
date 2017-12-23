package fr.jeantuffier.reminder.free.home.presentation

import fr.jeantuffier.reminder.free.common.model.Task
import no.hyper.memoryorm.Memory

class HomeModel(private val presenter: HomeContract.Presenter, private val memory: Memory) : HomeContract.Model {

    override fun createDatabase() {
        memory.deleteDatabase()
        memory.createDatabase()
    }

    override fun saveTask(task: Task) = memory.save(task)

    override fun loadData(): List<Task> = memory.fetchAll(Task::class.java)

    override fun deleteTask(id: String) = memory.deleteById(Task::class.java.simpleName, id)

}