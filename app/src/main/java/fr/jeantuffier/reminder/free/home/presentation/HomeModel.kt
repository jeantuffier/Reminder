package fr.jeantuffier.reminder.free.home.presentation

import fr.jeantuffier.reminder.free.common.model.Task
import no.hyper.memoryorm.Memory

class HomeModel(private val presenter: HomeContract.Presenter, private val memory: Memory) : HomeContract.Model {

    private val DB_VERSION = 6
    private val LOCAL_DB_VERSION = "HomeModel.LOCAL_DB_VERSION"

    private val tasks = mutableListOf<Task>()

    override fun createDatabase() {
        /*val localVersion = presenter.getApplicationContext()?.readPreference {
            it.getInt(LOCAL_DB_VERSION, 0)
        }
        if (localVersion != DB_VERSION) {
            memory.deleteDatabase()
            memory.createDatabase()
            presenter.getApplicationContext()?.editPreferences { it.putInt(LOCAL_DB_VERSION, DB_VERSION) }
        }*/
    }

    override fun getTaskCount() = tasks.size

    override fun getTask(position: Int): Task? = tasks[position]

    override fun saveTask(task: Task) = memory.save(task)

    override fun getPosition(task: Task) = tasks.indexOf(task)

    override fun loadData() {
        val fetchedTasks = memory.fetchAll(Task::class.java)
        fetchedTasks?.let {
            tasks.clear()
            tasks.addAll(fetchedTasks)
            tasks.sortBy { it.priority.getLevel() }
            tasks.reverse()
        }
    }

    override fun deleteTask(position: Int) {
        getTask(position)?.let {
            tasks.remove(it)
            memory.deleteById(Task::class.java.simpleName, it.id)
        }
    }

}