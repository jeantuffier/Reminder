package fr.jeantuffier.reminder.create.model

import no.hyper.memoryorm.Memory
import fr.jeantuffier.reminder.common.model.task.Task
import fr.jeantuffier.reminder.create.presenter.RequiredCreateTaskPresenterOps

/**
 * Created by jean on 08.11.2016.
 */
class CreateTaskModel(val presenter: RequiredCreateTaskPresenterOps) : ProvidedCreateTaskModelOps {

    override fun saveNewTask(task: Task) : Long? {
        presenter.getApplicationContext()?.let {
            return Memory(it).save(task)
        }
        return null
    }

    override fun getTaskCount(): Int {
        presenter.getActivityContext()?.let {
            return Memory(it).getTableCount(javaClass.simpleName)
        }
        return 0
    }

}