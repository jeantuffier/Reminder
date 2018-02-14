package fr.jeantuffier.reminder.free.home.presentation

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import fr.jeantuffier.reminder.free.common.dao.TaskDao
import fr.jeantuffier.reminder.free.common.extension.editPreferences
import fr.jeantuffier.reminder.free.common.extension.getIntent
import fr.jeantuffier.reminder.free.common.extension.withExtras
import fr.jeantuffier.reminder.free.common.model.Priority
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

    private var tasks : List<Task>? = null

    companion object {
        private const val DB_VERSION = 6
        private const val LOCAL_DB_VERSION = "HomeModel.LOCAL_DB_VERSION"
    }

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
        Observable.fromCallable { taskDao.deleteById(id) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
                unregisterAlarm(id)
                view.deleteTask(id)
            }
    }

    private fun unregisterAlarm(id: Int) {
        getTaskById(id)?.let {
            val intent = getTaskIntent(it)
            val pendingIntent = PendingIntent.getService(context, id, intent, 0)

            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.cancel(pendingIntent)
        }
    }

    private fun getTaskById(id: Int) = tasks?.first { it.id == id }

    private fun getTaskIntent(task: Task) = context.getIntent<DisplayNotificationService>()
        .withExtras {
            putInt(Task.ID, task.id)
            putString(Task.TITLE, task.title)
            putString(Task.PRIORITY, Priority.getByLevel(task.priority).toString())
            putString(Task.FROM, task.fromTime)
            putString(Task.TO, task.toTime)
        }

    override fun onDbCreated() = context.editPreferences { putInt(LOCAL_DB_VERSION, DB_VERSION) }

    override fun loadData() {
        taskDao.getAll()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe { tasks ->
                this.tasks = tasks
                tasks.sortedBy { it.priority }
                tasks.reversed()
                view.setTasks(tasks)
            }
    }

}