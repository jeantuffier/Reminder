package fr.jeantuffier.reminder.free.create.model

import fr.jeantuffier.reminder.free.common.model.Task
import fr.jeantuffier.reminder.free.create.presenter.RequiredCreateTaskPresenterOps

class CreateTaskModel(val presenter: RequiredCreateTaskPresenterOps) : ProvidedCreateTaskModelOps {



    override fun saveNewTask(task: Task) : Long? {
        val context = presenter.getApplicationContext()
        context ?: return null
        return 0//Memory(context).save(task)
    }

    override fun getTaskCount(): Int {
        val context = presenter.getApplicationContext()
        context ?: return 0
        return 0//Memory(context).getTableCount(Task::class.java.simpleName)
    }

    override fun getHighestTaskId(): Int? {
        val context = presenter.getApplicationContext()
        context ?: return null

        /*val list =  Memory(context).fetchAll(Task::class.java)
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
        }*/
        return 0
    }

}
