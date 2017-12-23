package fr.jeantuffier.reminder.free.home.presentation

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import fr.jeantuffier.reminder.free.common.extension.editPreferences
import fr.jeantuffier.reminder.free.common.extension.readPreference
import fr.jeantuffier.reminder.free.common.service.AbstractConnectionObserver
import fr.jeantuffier.reminder.free.common.service.DisplayNotificationService
import fr.jeantuffier.reminder.free.home.delegate.RegularTaskDelegate
import java.lang.ref.WeakReference

class HomePresenter(private val context: Context, private val view: WeakReference<HomeContract.View>) : HomeContract.Presenter, AbstractConnectionObserver() {

    companion object {
        private const val DB_VERSION = 6
        private const val LOCAL_DB_VERSION = "HomeModel.LOCAL_DB_VERSION"
    }

    lateinit var model: HomeContract.Model

    private var dnsLocalService: DisplayNotificationService.LocalBinder? = null
    private var taskId = ""

    override fun onDbCreated() = context.editPreferences { putInt(LOCAL_DB_VERSION, DB_VERSION) }

    override fun loadData() {
        init(context)
        if (!DisplayNotificationService.isRunning) {
            val intent = Intent(context, DisplayNotificationService::class.java)
            context.startService(intent)
        }

        val tasks = model.loadData()
        tasks.sortedBy { it.priority }
        tasks.reversed()
        view.get()?.let { it.setTasks(tasks) }
    }

    override fun deleteItem(id: String) = model.deleteTask(id)

    override fun onObserverConnected(className: ComponentName, service: IBinder) {
        dnsLocalService = service as DisplayNotificationService.LocalBinder
        dnsLocalService?.service?.deleteExistingTask(taskId)
    }

    override fun onObserverDisconnected(className: ComponentName) = Unit

    private fun init(context: Context) {
        val localVersion = context.readPreference { getInt(LOCAL_DB_VERSION, 0) }
        if (localVersion != DB_VERSION) {
            model.createDatabase()
        }
    }

}