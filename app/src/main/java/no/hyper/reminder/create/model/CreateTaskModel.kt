package no.hyper.reminder.create.model

import no.hyper.memoryorm.Memory
import no.hyper.reminder.common.model.task.Task
import no.hyper.reminder.create.presenter.RequiredCreateTaskPresenterOps

/**
 * Created by jean on 08.11.2016.
 */
class CreateTaskModel(val presenter: RequiredCreateTaskPresenterOps) : ProvidedCreateTaskModelOps {

    val memory by lazy { Memory(presenter.getContext()) }

    override fun saveNewTask(task: Task) : Long? {
        return memory.save(task)
    }

    override fun getTaskCount(): Int {
        return 3
    }

}