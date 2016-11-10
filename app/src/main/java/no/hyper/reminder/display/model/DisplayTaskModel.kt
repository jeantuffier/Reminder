package no.hyper.reminder.display.model

import no.hyper.memoryorm.Memory
import no.hyper.reminder.common.model.task.Task
import no.hyper.reminder.common.model.task.regular.RegularTask
import no.hyper.reminder.common.model.timer.Timer
import no.hyper.reminder.display.presenter.RequiredDisplayTaskPresenterOps

/**
 * Created by jean on 21.10.2016.
 */
class DisplayTaskModel(val presenter : RequiredDisplayTaskPresenterOps) : ProvidedDisplayTaskModelOps {

    private val LOG_TAG = this.javaClass.simpleName
    private val tasks = mutableListOf<Task>()
    private val memory by lazy { Memory(presenter.getApplicationContext()) }

    override fun getTaskCount() = tasks.size

    override fun getTask(position: Int) : Task? = tasks[position]

    override fun saveTask(task: RegularTask) = memory.save(task)

    override fun loadData() {
        val fetchedTasks = memory.fetchAll(RegularTask::class.java)
        fetchedTasks?.let {
            tasks.addAll(fetchedTasks)
        }
    }

}