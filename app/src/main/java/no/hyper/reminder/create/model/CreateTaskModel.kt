package no.hyper.reminder.create.model

import no.hyper.memoryorm.Memory
import no.hyper.reminder.common.model.task.Task
import no.hyper.reminder.common.model.task.regular.RegularTask
import no.hyper.reminder.create.presenter.RequiredCreateTaskPresenterOps

/**
 * Created by jean on 08.11.2016.
 */
class CreateTaskModel(val presenter: RequiredCreateTaskPresenterOps) : ProvidedCreateTaskModelOps {

    override fun saveNewTask(task: Task) : Long? {
        presenter.getActivityContext()?.let {
            return Memory(it).save(task)
        }
        return null
    }

    override fun getTaskCount(): Int? {
        presenter.getActivityContext()?.let {
            return Memory(it).getTableCount(RegularTask.javaClass.simpleName)
        }
        return null
    }

}