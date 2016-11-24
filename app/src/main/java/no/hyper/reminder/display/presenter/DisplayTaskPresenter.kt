package no.hyper.reminder.display.presenter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup
import no.hyper.reminder.R
import no.hyper.reminder.common.jobscheduler.JobManager
import no.hyper.reminder.common.model.task.Task
import no.hyper.reminder.display.model.ProvidedDisplayTaskModelOps
import no.hyper.reminder.common.model.task.ViewTypeFactory
import no.hyper.reminder.common.model.task.regular.RegularTaskViewHolder
import no.hyper.reminder.display.presenter.delegate.RegularTaskDelegate
import no.hyper.reminder.display.view.RequiredDisplayTaskViewOps
import java.lang.ref.WeakReference

/**
 * Created by Jean on 10/12/2016.
 */
class DisplayTaskPresenter(view: RequiredDisplayTaskViewOps) : ProvidedDisplayTaskPresenterOps,
        RequiredDisplayTaskPresenterOps {

    private val LOG_TAG = this.javaClass.simpleName
    private val factory = ViewTypeFactory()
    private val regularTaskDelegate = RegularTaskDelegate()

    private var viewReference : WeakReference<RequiredDisplayTaskViewOps>
    lateinit var model : ProvidedDisplayTaskModelOps

    init { viewReference = WeakReference(view) }

    override fun createDatabase() {
        model.createDatabase()
    }

    override fun loadData() = model.loadData()

    override fun getTasksCount() = model.getTaskCount()

    override fun getViewType(position: Int): Int {
        val task = model.getTask(position)
        return task?.getViewType(factory) ?: throw Exception("No view type for this position")
    }

    override fun createViewHolder(parent: ViewGroup?, viewType: Int): RegularTaskViewHolder {
        return when (viewType) {
            R.layout.list_task_view_item -> regularTaskDelegate.createViewHolder(parent, viewType)
            else -> throw Exception("No view holder type for this view type")
        }
    }

    override fun bindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            R.layout.list_task_view_item -> bindToRegularTask(holder, position)
        }
    }

    override fun deleteItem(position: Int) {
        model.getTask(position)?.let { task ->
            model.deleteTask(task)
            viewReference.get()?.getApplicationContext()?.let {
                JobManager.unregisterJob(it, task)
            }
        }
    }

    override fun getApplicationContext() = viewReference.get()?.getApplicationContext()

    override fun getActivityContext() = viewReference.get()?.getActivityContext()

    private fun addDeleteActionToMenu(position: Int) : Boolean {
        viewReference.get()?.addLongItemClickMenuOptionsFor(position)
        return true
    }

    private fun bindToRegularTask(holder: RecyclerView.ViewHolder, position: Int) {
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

}