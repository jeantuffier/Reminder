package no.hyper.reminder.display.model

import no.hyper.memoryorm.Memory
import no.hyper.reminder.common.extension.editPreferences
import no.hyper.reminder.common.extension.readPreference
import no.hyper.reminder.common.model.task.Task
import no.hyper.reminder.common.model.task.regular.RegularTask
import no.hyper.reminder.display.presenter.RequiredDisplayTaskPresenterOps

/**
 * Created by jean on 21.10.2016.
 */
class DisplayTaskModel(val presenter : RequiredDisplayTaskPresenterOps) : ProvidedDisplayTaskModelOps {

    private val DB_VERSION = 2
    private val LOCAL_DB_VERSION = "DisplayTaskModel.LOCAL_DB_VERSION"

    private val tasks = mutableListOf<Task>()
    private val memory by lazy { Memory(presenter.getApplicationContext()) }

    override fun createDatabase() {
        val localVersion = presenter.getApplicationContext()?.readPreference {
            it.getInt(LOCAL_DB_VERSION, 0)
        }
        if (localVersion != DB_VERSION) {
            memory.deleteDatabase()
            memory.createDatabase()
            presenter.getApplicationContext()?.editPreferences { it.putInt(LOCAL_DB_VERSION, DB_VERSION) }
        }
    }

    override fun getTaskCount() = tasks.size

    override fun getTask(position: Int) : Task? = tasks[position]

    override fun saveTask(task: RegularTask) = memory.save(task)

    override fun getPosition(task: Task) = tasks.indexOf(task)

    override fun loadData() {
        val fetchedTasks = memory.fetchAll(RegularTask::class.java)
        fetchedTasks?.let {
            tasks.clear()
            tasks.addAll(fetchedTasks)
        }
    }

    override fun deleteTask(task: Task) {
        tasks.remove(task)
        memory.deleteById(task.javaClass.simpleName, task.getId())
    }

}