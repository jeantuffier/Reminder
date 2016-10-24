package no.hyper.reminder.list.presenter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import no.hyper.reminder.R
import no.hyper.reminder.list.model.ProvidedTaskModelOps
import no.hyper.reminder.common.model.ViewTypeFactory
import no.hyper.reminder.common.model.regular.RegularTaskViewHolder
import no.hyper.reminder.list.presenter.delegate.RegularTaskDelagate
import no.hyper.reminder.list.view.RequiredTaskListViewOps
import java.lang.ref.WeakReference

/**
 * Created by Jean on 10/12/2016.
 */
class TaskListPresenter(view: RequiredTaskListViewOps, val model : ProvidedTaskModelOps)
    : ProvidedTaskListPresenterOps, RequiredTaskListPresenterOps {

    private var viewReference : WeakReference<RequiredTaskListViewOps>
    private val factory = ViewTypeFactory()

    private val regularTaskDelegate = RegularTaskDelagate()

    init {
        viewReference = WeakReference(view)
    }

    override fun getTasksCount() = model.getTaskCount()

    override fun getViewType(position: Int): Int? {
        val task = model.getTask(position)
        return task?.getViewType(factory)
    }

    override fun createViewHolder(parent: ViewGroup?, viewType: Int): RegularTaskViewHolder? {
        return when (viewType) {
            R.layout.view_item_task_list -> regularTaskDelegate.createViewHolder(parent, viewType)
            else -> null
        }
    }

    override fun bindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            R.layout.view_item_task_list -> regularTaskDelegate.bindViewHolder(holder, position)
        }
    }

    override fun getApplicationContext() = viewReference.get()?.getApplicationContext()

    override fun getActivityContext() = viewReference.get()?.getActivityContext()

}