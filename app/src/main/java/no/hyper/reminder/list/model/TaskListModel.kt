package no.hyper.reminder.list.model

import no.hyper.memoryorm.Memory
import no.hyper.reminder.common.model.Priority
import no.hyper.reminder.common.model.Task
import no.hyper.reminder.common.model.regular.RegularTask
import no.hyper.reminder.list.presenter.RequiredTaskListPresenterOps

/**
 * Created by jean on 21.10.2016.
 */
class TaskListModel(val presenter : RequiredTaskListPresenterOps) : ProvidedTaskModelOps{

    private val LOG_TAG = this.javaClass.simpleName
    private val tasks = mutableListOf<Task>()
    private val memory by lazy { Memory(presenter.getApplicationContext()) }

    init {
        tasks.add(RegularTask("Duolingo", Priority.LOW, "01/01/2017"))
        tasks.add(RegularTask("sortir les poubelles", Priority.MIDDLE, "01/01/2017"))
        tasks.add(RegularTask("aller acheter des pneus de velo", Priority.HIGH, "01/01/2017"))
    }

    override fun getTaskCount() = tasks.size

    override fun getTask(position: Int) : Task? = tasks[position]

    override fun saveTask(task: RegularTask) = memory.save(task)

    override fun loadData(): Boolean {
        tasks.addAll(memory.fetchAll(Task::class.java))
        return tasks.size > 0
    }

}