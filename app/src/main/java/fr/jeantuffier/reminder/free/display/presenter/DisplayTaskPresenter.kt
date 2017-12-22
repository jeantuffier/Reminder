package fr.jeantuffier.reminder.free.display.presenter

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import fr.jeantuffier.reminder.free.common.service.DisplayNotificationService
import fr.jeantuffier.reminder.free.common.service.AbstractConnectionObserver
import fr.jeantuffier.reminder.free.display.model.ProvidedDisplayTaskModelOps
import fr.jeantuffier.reminder.free.display.presenter.delegate.RegularTaskDelegate
import fr.jeantuffier.reminder.free.display.view.RequiredDisplayTaskViewOps
import java.lang.ref.WeakReference

/**
 * Created by Jean on 10/12/2016.
 */
class DisplayTaskPresenter(val view: WeakReference<RequiredDisplayTaskViewOps>) : AbstractConnectionObserver(),
        ProvidedDisplayTaskPresenterOps, RequiredDisplayTaskPresenterOps {

    lateinit var model : ProvidedDisplayTaskModelOps
    private val regularTaskDelegate = RegularTaskDelegate()
    private var dnsLocalService : DisplayNotificationService.LocalBinder? = null
    private var taskId = ""

    override fun createDatabase() {
        model.createDatabase()
    }

    override fun loadData() {
        if (!DisplayNotificationService.isRunning) {
            val context = getActivityContext()
            context ?: return

            val intent = Intent(context, DisplayNotificationService::class.java)
            context.startService(intent)
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
            view.get()?.getApplicationContext()?.let {
                val intent = Intent(it, DisplayNotificationService::class.java)
                it.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
            }
            model.deleteTask(position)
        }
    }

    override fun getApplicationContext() = view.get()?.getApplicationContext()

    override fun getActivityContext() = view.get()?.getActivityContext()

    override fun onObserverConnected(className: ComponentName, service: IBinder) {
        dnsLocalService = service as DisplayNotificationService.LocalBinder
        dnsLocalService?.service?.deleteExistingTask(taskId)
    }

    override fun onObserverDisconnected(className: ComponentName) = Unit

    private fun addDeleteActionToMenu(position: Int) : Boolean {
        view.get()?.addLongItemClickMenuOptionsFor(position)
        return true
    }

}