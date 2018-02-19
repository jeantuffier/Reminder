package fr.jeantuffier.reminder.free.home.presentation

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import fr.jeantuffier.reminder.free.common.dao.TaskDao
import fr.jeantuffier.reminder.free.common.model.Task
import fr.jeantuffier.reminder.free.common.service.DisplayNotificationService
import fr.jeantuffier.reminder.free.home.presentation.delegate.ItemClickDispatcher
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class HomePresenter(
    private val context: Context,
    private val view: HomeContract.View,
    private val taskDao: TaskDao
) : HomeContract.Presenter {

    private var tasks: MutableList<Task>? = null

    init {
        setItemClickDispatcher()
    }

    private fun setItemClickDispatcher() {
        ItemClickDispatcher.publishSubject = PublishSubject.create()
        ItemClickDispatcher.publishSubject?.subscribe {
            deleteItem(it)
        }
    }

    private fun deleteItem(id: Int) {
        val position = getItemPosition(id)
        view.deleteTask(position)

        getTaskById(id)?.let {
            unregisterAlarm(it)
            tasks?.remove(it)
        }

        deleteItemFromDb(id)
    }

    private fun deleteItemFromDb(id: Int) {
        Observable.fromCallable { taskDao.deleteById(id) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    private fun getTaskById(id: Int) = tasks?.first { it.id == id }

    private fun unregisterAlarm(task: Task) {
        val intent = DisplayNotificationService.getIntent(context, task)
        val pendingIntent = PendingIntent.getService(context, task.id, intent, 0)

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(pendingIntent)
    }

    private fun getItemPosition(id: Int): Int {
        val task = tasks?.find { it.id == id }
        return if (task != null) {
            tasks?.indexOf(task) ?: -1
        } else {
            -1
        }
    }

    override fun loadData() {
        taskDao.getAll()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe { tasks ->
                this.tasks = tasks.toMutableList()
                tasks.sortedBy { it.priority }
                tasks.reversed()
                view.setTasks(tasks)
            }
    }

}
