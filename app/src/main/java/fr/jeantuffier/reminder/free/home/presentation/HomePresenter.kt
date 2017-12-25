package fr.jeantuffier.reminder.free.home.presentation

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.IBinder
import fr.jeantuffier.reminder.free.common.dao.TaskDao
import fr.jeantuffier.reminder.free.common.extension.editPreferences
import fr.jeantuffier.reminder.free.common.service.AbstractConnectionObserver
import fr.jeantuffier.reminder.free.common.service.DisplayNotificationService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomePresenter(
        private val context: Context,
        private val view: HomeContract.View
) : HomeContract.Presenter, AbstractConnectionObserver() {

    companion object {
        private const val DB_VERSION = 6
        private const val LOCAL_DB_VERSION = "HomeModel.LOCAL_DB_VERSION"
    }

    @Inject
    private lateinit var taskDao: TaskDao

    private var dnsLocalService: DisplayNotificationService.LocalBinder? = null
    private var taskId = ""

    init {
        if (!DisplayNotificationService.isRunning) {
            val intent = Intent(context, DisplayNotificationService::class.java)
            context.startService(intent)
        }
    }

    override fun onDbCreated() = context.editPreferences { putInt(LOCAL_DB_VERSION, DB_VERSION) }

    override fun loadData() {
        taskDao.getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe {
                    it.sortedBy { it.priority }
                    it.reversed()
                    view.setTasks(it)
                }
    }

    override fun deleteItem(id: Int) {
        taskDao.deleteById(id)
    }

    override fun onObserverConnected(className: ComponentName, service: IBinder) {
        dnsLocalService = service as DisplayNotificationService.LocalBinder
        dnsLocalService?.service?.deleteExistingTask(taskId)
    }

    override fun onObserverDisconnected(className: ComponentName) = Unit

}