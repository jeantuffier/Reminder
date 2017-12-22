package fr.jeantuffier.reminder.free.home.presentation

import android.content.ComponentName
import android.os.IBinder
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import fr.jeantuffier.reminder.free.common.service.AbstractConnectionObserver
import fr.jeantuffier.reminder.free.common.service.DisplayNotificationService
import fr.jeantuffier.reminder.free.home.delegate.RegularTaskDelegate
import java.lang.ref.WeakReference

class HomePresenter(val view: WeakReference<HomeContract.View>) : HomeContract.Presenter, AbstractConnectionObserver() {

    lateinit var model: HomeContract.Model
    private val regularTaskDelegate = RegularTaskDelegate()
    private var dnsLocalService: DisplayNotificationService.LocalBinder? = null
    private var taskId = ""

    override fun createDatabase() {
        model.createDatabase()
    }

    override fun loadData() {
        if (!DisplayNotificationService.isRunning) {
            /*context ?: return

            val intent = Intent(context, DisplayNotificationService::class.java)
            context.startService(intent)*/
        }

        model.loadData()
    }

    override fun getTasksCount() = model.getTaskCount()

    override fun createViewHolder(parent: ViewGroup?, viewType: Int)
            = regularTaskDelegate.createViewHolder(parent, viewType)

    override fun bindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val task = model.getTask(position)
        regularTaskDelegate.bindViewHolder(holder, task)
        holder.itemView.setOnLongClickListener {
            task?.let {
                model.getPosition(it)?.let {
                    addDeleteActionToMenu(it)
                }
            }
            true
        }
    }

    override fun deleteItem(position: Int) {
        model.getTask(position)?.let {
            taskId = it.id
            /*view.get()?.getApplicationContext()?.let {
                val intent = Intent(it, DisplayNotificationService::class.java)
                it.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
            }*/
            model.deleteTask(position)
        }
    }

    override fun onObserverConnected(className: ComponentName, service: IBinder) {
        dnsLocalService = service as DisplayNotificationService.LocalBinder
        dnsLocalService?.service?.deleteExistingTask(taskId)
    }

    override fun onObserverDisconnected(className: ComponentName) = Unit

    private fun addDeleteActionToMenu(position: Int): Boolean {
        view.get()?.addLongItemClickMenuOptionsFor(position)
        return true
    }

}