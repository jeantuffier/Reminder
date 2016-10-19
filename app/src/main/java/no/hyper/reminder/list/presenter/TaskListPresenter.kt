package no.hyper.reminder.list.presenter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import no.hyper.reminder.R
import no.hyper.reminder.common.model.ProvidedTaskModelOps
import no.hyper.reminder.common.model.ViewTypeFactory
import no.hyper.reminder.common.model.regular.RegularTaskViewHolder
import no.hyper.reminder.list.presenter.delegate.RegularTaskDelagate
import no.hyper.reminder.list.view.RequiredTaskListViewOps
import java.lang.ref.WeakReference

/**
 * Created by Jean on 10/12/2016.
 */
class TaskListPresenter(view: RequiredTaskListViewOps)
    : ProvidedTaskListPresenterOps, RequiredTaskListPresenterOps {

    private lateinit var model : ProvidedTaskModelOps

    private var viewReference : WeakReference<RequiredTaskListViewOps>
    private val factory = ViewTypeFactory()

    private val regularTaskDelegate = RegularTaskDelagate()

    init {
        viewReference = WeakReference(view)
    }

    override fun createNewTask() {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTasksCount() = model.getTaskCount()

    override fun getViewType(position: Int): Int {
        val task = model.getTask(position)
        return task.getViewType(factory)
    }

    override fun createViewHolder(parent: ViewGroup?, viewType: Int): RegularTaskViewHolder {
        return when (viewType) {
            R.layout.view_item_task_list -> regularTaskDelegate.createViewHolder(parent, viewType)
            else -> throw Exception("No ViewHolder for view type: $viewType")
        }
    }

    override fun bindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            R.layout.view_item_task_list -> regularTaskDelegate.bindViewHolder(holder, position)
        }
    }

    override fun getApplicationContext(): Context {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getActivityContext(): Context {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}