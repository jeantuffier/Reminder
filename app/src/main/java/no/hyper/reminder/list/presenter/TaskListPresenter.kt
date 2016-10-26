package no.hyper.reminder.list.presenter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup
import no.hyper.reminder.R
import no.hyper.reminder.list.model.ProvidedTaskModelOps
import no.hyper.reminder.common.model.ViewTypeFactory
import no.hyper.reminder.common.model.regular.RegularTaskViewHolder
import no.hyper.reminder.list.presenter.delegate.RegularTaskDelegate
import no.hyper.reminder.list.view.RequiredTaskListViewOps
import java.lang.ref.WeakReference

/**
 * Created by Jean on 10/12/2016.
 */
class TaskListPresenter(view: RequiredTaskListViewOps)
    : ProvidedTaskListPresenterOps, RequiredTaskListPresenterOps {

    private val LOG_TAG = this.javaClass.simpleName
    private val factory = ViewTypeFactory()
    private val regularTaskDelegate = RegularTaskDelegate()

    private var viewReference : WeakReference<RequiredTaskListViewOps>
    lateinit var model : ProvidedTaskModelOps

    init {
        viewReference = WeakReference(view)
    }

    override fun getTasksCount() = model.getTaskCount()

    override fun getViewType(position: Int): Int {
        val task = model.getTask(position)
        Log.d(LOG_TAG, "get view type for task ${task?.getName()} at position $position")
        return task?.getViewType(factory) ?: throw Exception("No view type for this position")
    }

    override fun createViewHolder(parent: ViewGroup?, viewType: Int): RegularTaskViewHolder {
        return when (viewType) {
            R.layout.view_item_task_list -> regularTaskDelegate.createViewHolder(parent, viewType)
            else -> throw Exception("No view holder type for this view type")
        }
    }

    override fun bindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            R.layout.view_item_task_list -> regularTaskDelegate.bindViewHolder(holder, model.getTask(position))
        }
    }

    override fun getApplicationContext() = viewReference.get()?.getApplicationContext()

    override fun getActivityContext() = viewReference.get()?.getActivityContext()

}