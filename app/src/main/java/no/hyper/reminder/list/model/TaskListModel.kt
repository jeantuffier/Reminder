package no.hyper.reminder.list.model

import no.hyper.memoryorm.Memory
import no.hyper.reminder.common.model.task.Task
import no.hyper.reminder.common.model.task.regular.RegularTask
import no.hyper.reminder.common.model.timer.Timer
import no.hyper.reminder.list.presenter.RequiredTaskListPresenterOps

/**
 * Created by jean on 21.10.2016.
 */
class TaskListModel(val presenter : RequiredTaskListPresenterOps) : ProvidedTaskModelOps{

    private val LOG_TAG = this.javaClass.simpleName
    private val tasks = mutableListOf<Task>()
    private val memory by lazy { Memory(presenter.getApplicationContext()) }

    init {
        val timer = Timer(Timer.Frequency.HOURS, 1, Timer.Alarm.NOTIFICATION)
        tasks.add(RegularTask("Duolingo", Task.Priority.LOW, timer))
        tasks.add(RegularTask("sortir les poubelles", Task.Priority.MIDDLE, timer))
        tasks.add(RegularTask("aller acheter des pneus de velo", Task.Priority.HIGH, timer))
    }

    override fun getTaskCount() = tasks.size

    override fun getTask(position: Int) : Task? = tasks[position]

    override fun saveTask(task: RegularTask) = memory.save(task)

    override fun loadData(): Boolean {
        tasks.addAll(memory.fetchAll(Task::class.java))
        return tasks.size > 0
    }

}