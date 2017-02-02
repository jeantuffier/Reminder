package fr.jeantuffier.reminder.display.presenter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import fr.jeantuffier.reminder.common.jobscheduler.JobManager
import fr.jeantuffier.reminder.display.model.ProvidedDisplayTaskModelOps
import fr.jeantuffier.reminder.display.presenter.delegate.RegularTaskDelegate
import fr.jeantuffier.reminder.display.view.RequiredDisplayTaskViewOps
import java.lang.ref.WeakReference

/**
 * Created by Jean on 10/12/2016.
 */
class DisplayTaskPresenter(val view: WeakReference<RequiredDisplayTaskViewOps>) : ProvidedDisplayTaskPresenterOps,
        RequiredDisplayTaskPresenterOps {

    private val regularTaskDelegate = RegularTaskDelegate()
    lateinit var model : ProvidedDisplayTaskModelOps

    override fun createDatabase() {
        model.createDatabase()
    }

    override fun loadData() = model.loadData()

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
        model.getTask(position)?.let { task ->
            model.deleteTask(task)
            view.get()?.getApplicationContext()?.let {
                JobManager.unregisterJob(it, task.id)
            }
        }
    }

    override fun getApplicationContext() = view.get()?.getApplicationContext()

    override fun getActivityContext() = view.get()?.getActivityContext()

    private fun addDeleteActionToMenu(position: Int) : Boolean {
        view.get()?.addLongItemClickMenuOptionsFor(position)
        return true
    }

}