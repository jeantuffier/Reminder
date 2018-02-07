package fr.jeantuffier.reminder.free.home.presentation

import android.content.Context
import fr.jeantuffier.reminder.free.common.dao.TaskDao
import fr.jeantuffier.reminder.free.common.extension.editPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomePresenter(
    private val context: Context,
    private val view: HomeContract.View,
    private val taskDao: TaskDao
) : HomeContract.Presenter {

    companion object {
        private const val DB_VERSION = 6
        private const val LOCAL_DB_VERSION = "HomeModel.LOCAL_DB_VERSION"
    }

    override fun onDbCreated() = context.editPreferences { putInt(LOCAL_DB_VERSION, DB_VERSION) }

    override fun loadData() {
        taskDao.getAll()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe { tasks ->
                tasks.sortedBy { it.priority }
                tasks.reversed()
                view.setTasks(tasks)
            }
    }

    override fun deleteItem(id: Int) {
        taskDao.deleteById(id)
    }

}