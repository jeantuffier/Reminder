package fr.jeantuffier.reminder.free.create.model

import no.hyper.memoryorm.Memory
import fr.jeantuffier.reminder.free.common.model.Task
import fr.jeantuffier.reminder.free.create.presenter.RequiredCreateTaskPresenterOps

/**
 * Created by jean on 08.11.2016.
 */
class CreateTaskModel(val presenter: RequiredCreateTaskPresenterOps) : ProvidedCreateTaskModelOps {



    override fun saveNewTask(task: Task) : Long? {
        val context = presenter.getApplicationContext()
        context ?: return null
        return Memory(context).save(task)
    }

    override fun getTaskCount(): Int {
        val context = presenter.getApplicationContext()
        context ?: return 0
        return Memory(context).getTableCount(Task::class.java.simpleName)
    }

    override fun getHighestTaskId(): Int? {
        val context = presenter.getApplicationContext()
        context ?: return null

        val list =  Memory(context).fetchAll(Task::class.java)
        if (list == null || list.isEmpty()) {
            return 0
        } else {
            var temp = 0
            list.forEach {
                if (it.id.toInt() > temp) {
                    temp = it.id.toInt()
                }
            }
            return temp
        }
    }

}
